package hairSalonReservation.sideProject.domain.serviceMenu.controller;

import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.service.ServiceMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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




}
