package hairSalonReservation.sideProject.common.image.service;

import hairSalonReservation.sideProject.common.dto.PresignedUrlResponse;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ExternalServiceException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageService {

    @Value("${image.bucket}")
    String bucket;

    private final MinioClient minioClient;

    public PresignedUrlResponse generatePresignedUploadUrl(String domainPrefix, String objectName) {

        String imageKey = createImageKey(domainPrefix, objectName);

        GetPresignedObjectUrlArgs preUrlRequest = GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(bucket)
                .object(imageKey)
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
        return domainPrefix + (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE) + imageName;
    }
}
