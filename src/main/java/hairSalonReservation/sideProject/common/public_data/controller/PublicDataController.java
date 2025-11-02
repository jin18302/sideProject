package hairSalonReservation.sideProject.common.public_data.controller;

import hairSalonReservation.sideProject.common.public_data.dto.response.ReadAddrResponse;
import hairSalonReservation.sideProject.common.public_data.service.PublicDataClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class PublicDataController {

    private final PublicDataClient addressSearchService;


    @GetMapping("/address")
    public ResponseEntity<List<ReadAddrResponse>> getRegionsByParent(@RequestParam(name="code", required = false)String code){

        List<ReadAddrResponse> responseList = addressSearchService.sendRequest(code);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
