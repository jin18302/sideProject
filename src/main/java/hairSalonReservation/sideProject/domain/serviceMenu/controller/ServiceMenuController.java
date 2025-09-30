package hairSalonReservation.sideProject.domain.serviceMenu.controller;

import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.UpdateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.service.ServiceMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceMenuController {

    private final ServiceMenuService serviceMenuService;

    @PostMapping("/designers/{designerId}/service-menus")
    public ResponseEntity<ServiceMenuResponse> createServiceMenu(
            @RequestAttribute(name = "userId")Long userId,
            @PathVariable(name = "designerId")Long designerId,
            @RequestBody @Valid CreateServiceMenuRequest request
    ) {
        ServiceMenuResponse serviceMenuResponse = serviceMenuService.createServiceMenu(userId, designerId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceMenuResponse);
    }

    @GetMapping("/designers/{designerId}/service-menus")
    public ResponseEntity<List<ServiceMenuResponse>> readByDesignerAndCategory(
            @PathVariable(name = "designerId") Long designerId,
            @RequestParam(name = "category") String category
    ) {
        List<ServiceMenuResponse> serviceMenuResponseList = serviceMenuService.readByDesignerAndCategory(designerId, category);
        return ResponseEntity.status(HttpStatus.OK).body(serviceMenuResponseList);
    }

    @PatchMapping("/service-menus/{serviceMenuId}")
    public ResponseEntity<ServiceMenuResponse> updateServiceMenu(
            @RequestAttribute("userId") Long userId,
            @PathVariable(name = "serviceMenuId") Long serviceMenuId,
            @RequestBody @Valid UpdateServiceMenuRequest request
    ) {
        ServiceMenuResponse serviceMenuResponse = serviceMenuService.updateServiceMenu(userId, serviceMenuId, request);
        return ResponseEntity.status(HttpStatus.OK).body(serviceMenuResponse);
    }

    @DeleteMapping("/service-menus/{serviceMenuId}")
    public ResponseEntity<Void> deleteServiceMenu(
            @RequestAttribute("userId") Long userId,
            @PathVariable(name = "serviceMenuId") Long serviceMenuId) {
        serviceMenuService.deleteServiceMenu(userId, serviceMenuId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
