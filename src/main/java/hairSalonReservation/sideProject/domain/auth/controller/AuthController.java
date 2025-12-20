package hairSalonReservation.sideProject.domain.auth.controller;

import hairSalonReservation.sideProject.domain.auth.service.AuthService;
import hairSalonReservation.sideProject.domain.auth.dto.request.LoginRequest;
import hairSalonReservation.sideProject.domain.auth.dto.response.LoginResponse;
import hairSalonReservation.sideProject.domain.auth.dto.request.SignUpRequest;
import hairSalonReservation.sideProject.domain.auth.dto.response.SignUpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignUpRequest request) {

        SignUpResponse signup = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(signup);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        LoginResponse loginResponse = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}