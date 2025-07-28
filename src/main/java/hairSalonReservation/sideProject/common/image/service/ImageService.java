package hairSalonReservation.sideProject.common.image.service;

import hairSalonReservation.sideProject.common.dto.PresignedUrlResponse;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ExternalServiceException;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageService {

    @Value("${minio.bucket}")
    String bucket;

    private final MinioClient minioClient;

    public PresignedUrlResponse generatePresignedUploadUrl(String domainPrefix, String objectName) {

        String imageKey = createImageKey(domainPrefix, objectName);

        log.info("key:{}", imageKey);

        GetPresignedObjectUrlArgs preUrlRequest = GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(bucket)
                .object(imageKey)// 혹시 모를 이미지 이름이 같을 경우를 대비해 uuid사용
                .expiry(600)
                .build();

        try{
            String url = minioClient.getPresignedObjectUrl(preUrlRequest);
            return PresignedUrlResponse.of(url);
        }catch (Exception e){
            throw new ExternalServiceException(ErrorCode.IMAGE_UPLOAD_FAILED);
        }
    }

    public PresignedUrlResponse generatePresignedAccessUrl(String fileName){

        GetPresignedObjectUrlArgs getUrlRequest = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(fileName)
                .expiry(600)
                .build();

        try{
            String url = minioClient.getPresignedObjectUrl(getUrlRequest);
            return PresignedUrlResponse.of(url);
        }catch (Exception e){
            throw new ExternalServiceException(ErrorCode.IMAGE_VIEW_FAILED);
        }
    }

    private String createImageKey(String domainPrefix, String imageName){
        LocalDate today = LocalDate.now();

        return domainPrefix + today.format(DateTimeFormatter.ofPattern("/yyyy/MM/dd/"))
               + (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE) + imageName;
    }


    @PostConstruct
    private void isExistBucket() {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception ex) {
            throw new ExternalServiceException(ErrorCode.MINIO_INITIALIZATION_FAILED);
        }
    }
}
