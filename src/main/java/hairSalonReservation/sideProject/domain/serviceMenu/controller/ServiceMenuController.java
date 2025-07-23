package hairSalonReservation.sideProject.domain.serviceMenu.controller;

import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.UpdateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.service.ServiceMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceMenuController {

    private final ServiceMenuService serviceMenuService;

    @PostMapping("/designers/service-categories/{serviceCategoryMapperId}")
    public ResponseEntity<ServiceMenuResponse> createServiceMenu(
            @PathVariable(name = "serviceCategoryMapperId") Long serviceCategoryMapperId,
            @RequestBody CreateServiceMenuRequest request
            ){
        ServiceMenuResponse serviceMenuResponse = serviceMenuService.createServiceMenu(serviceCategoryMapperId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceMenuResponse);
    }

    @GetMapping("/designers/service-categories/{serviceCategoryMapperId}")
    public ResponseEntity<List<ServiceMenuResponse>> readAllByDesignerServiceCategory(
            @PathVariable(name = "serviceCategoryMapperId") Long serviceCategoryMapperId
    ){
        List<ServiceMenuResponse> serviceMenuResponseList = serviceMenuService.readAllByDesignerServiceCategory(serviceCategoryMapperId);
        return ResponseEntity.status(HttpStatus.OK).body(serviceMenuResponseList);
    }

    @PatchMapping("/service-menu/{serviceMenuId}")
    public ResponseEntity<ServiceMenuResponse> updateServiceMenu(
            @PathVariable(name = "serviceMenuId") Long serviceMenuId,
            @RequestBody UpdateServiceMenuRequest request
            ){
        ServiceMenuResponse serviceMenuResponse = serviceMenuService.updateServiceMenu(serviceMenuId, request);
        return ResponseEntity.status(HttpStatus.OK).body(serviceMenuResponse);
    }

    @DeleteMapping("/service-menu/{serviceMenuId}")
    public ResponseEntity<Void> deleteServiceMenu(@PathVariable(name = "serviceMenuId") Long serviceMenuId) {
        serviceMenuService.deleteServiceMenu(serviceMenuId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
