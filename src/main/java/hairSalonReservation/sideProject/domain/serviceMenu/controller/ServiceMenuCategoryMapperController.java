package hairSalonReservation.sideProject.domain.serviceMenu.controller;

import hairSalonReservation.sideProject.domain.serviceMenu.dto.request.ServiceMenuCategoryMapperRequest;
import hairSalonReservation.sideProject.domain.serviceMenu.dto.response.ServiceMenuCategoryMapperResponse;
import hairSalonReservation.sideProject.domain.serviceMenu.service.ServiceMenuCategoryMapperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ServiceMenuCategoryMapperController {

    private final ServiceMenuCategoryMapperService serviceMenuCategoryMapperService;

    @PostMapping("/designer/{designerId}/serviceMenuCategories")
    public ResponseEntity<List<ServiceMenuCategoryMapperResponse>> createServiceCategoryMapper(
            @PathVariable(name = "designerId") Long designerId,
            @RequestBody @Valid ServiceMenuCategoryMapperRequest request
            ){
        List<ServiceMenuCategoryMapperResponse> mapperResponseList = serviceMenuCategoryMapperService.createServiceCategoryMapper(designerId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperResponseList);
    }

    @GetMapping("/designer/{designerId}/serviceMenuCategories")
    public ResponseEntity<List<ServiceMenuCategoryMapperResponse>> readByDesignerId(@PathVariable(name = "designerId") Long designerId){

        List<ServiceMenuCategoryMapperResponse> mapperResponseList = serviceMenuCategoryMapperService.readByDesignerId(designerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperResponseList);
    }

    @PatchMapping("/designer/{designerId}/serviceMenuCategories")
    public ResponseEntity<List<ServiceMenuCategoryMapperResponse>> updateServiceCategoryMapper(
            @PathVariable(name = "designerId") Long designerId,
            @RequestBody @Valid ServiceMenuCategoryMapperRequest request
    ){
        List<ServiceMenuCategoryMapperResponse> mapperResponseList = serviceMenuCategoryMapperService.updateServiceCategoryMapper(designerId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperResponseList);
    }
}
