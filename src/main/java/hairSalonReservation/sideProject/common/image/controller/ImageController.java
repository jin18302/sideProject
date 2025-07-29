package hairSalonReservation.sideProject.common.image.controller;

import hairSalonReservation.sideProject.common.dto.PresignedUrlResponse;
import hairSalonReservation.sideProject.common.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/images/upload/{domainPrefix}/{imageName}")
    public ResponseEntity<PresignedUrlResponse> getPresignedUploadUrl(@PathVariable(name = "imageName") String imageName,
                                                                      @PathVariable(name = "domainPrefix") String domainPrefix) {
        return ResponseEntity.ok(imageService.generatePresignedUploadUrl(domainPrefix, imageName));
    }


    @GetMapping("/images/view/{imageName}")
    public ResponseEntity<PresignedUrlResponse> getPresignedViewUrl(@PathVariable(name = "imageName") String imageName) {
        return ResponseEntity.ok( imageService.generatePresignedAccessUrl(imageName));
    }
}