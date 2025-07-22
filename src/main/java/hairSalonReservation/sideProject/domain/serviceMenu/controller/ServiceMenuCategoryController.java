package hairSalonReservation.sideProject.domain.serviceMenu.controller;

import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.CreateServiceMenuCategoryRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.UpdateServiceMenuCategoryRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuCategoryResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.service.ServiceMenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ServiceMenuCategoryController {

    private final ServiceMenuCategoryService serviceMenuCategoryService;

    @PostMapping("/service-menu-categories")
    public ResponseEntity<ServiceMenuCategoryResponse> createServiceMenuCategory(@RequestBody CreateServiceMenuCategoryRequest request){

        ServiceMenuCategoryResponse serviceMenuCategoryResponse = serviceMenuCategoryService.createServiceMenuCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceMenuCategoryResponse);
    }

    @PatchMapping("/service-menu-categories/{serviceMenuCategoryId}")
    public ResponseEntity<ServiceMenuCategoryResponse> updateServiceMenuCategory(
            @PathVariable(name = "serviceMenuCategoryId") Long serviceMenuCategoryId,
            @RequestBody UpdateServiceMenuCategoryRequest request
    ){

        ServiceMenuCategoryResponse serviceMenuCategoryResponse = serviceMenuCategoryService.updateServiceMenuCategory(serviceMenuCategoryId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceMenuCategoryResponse);
    }
}
