package hairSalonReservation.sideProject.domain.reservation.controller;

import hairSalonReservation.sideProject.domain.reservation.dto.request.CreateReservationRequest;
import hairSalonReservation.sideProject.domain.reservation.dto.response.CreateReservationResponse;
import hairSalonReservation.sideProject.domain.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/designers/{designerId}/reservations")
    public ResponseEntity<CreateReservationResponse> createReservation(
            @RequestAttribute("userId") Long userId,
            @PathVariable(name = "designerId") Long designerId,
            @RequestBody @Valid CreateReservationRequest request){

        CreateReservationResponse response = reservationService.createReservation(userId, designerId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
