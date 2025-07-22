package hairSalonReservation.sideProject.domain.designer.controller;

import hairSalonReservation.sideProject.domain.designer.dto.request.CreateDesignerRequest;
import hairSalonReservation.sideProject.domain.designer.dto.response.DesignerResponse;
import hairSalonReservation.sideProject.domain.designer.service.DesignerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class DesignerController {

    private final DesignerService designerService;


    @PostMapping("/shops/{shopId}/designers")
    public ResponseEntity<DesignerResponse> createDesigner(
            @PathVariable(name = "shopId") Long shopId,
            @RequestAttribute("userId") Long userId,
            @RequestBody CreateDesignerRequest request
    ){
        DesignerResponse designerResponse = designerService.createDesigner(shopId, userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(designerResponse);
    }

}
