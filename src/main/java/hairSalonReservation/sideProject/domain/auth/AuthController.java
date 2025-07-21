package hairSalonReservation.sideProject.domain.auth;

import hairSalonReservation.sideProject.domain.auth.dto.LoginRequest;
import hairSalonReservation.sideProject.domain.auth.dto.LoginResponse;
import hairSalonReservation.sideProject.domain.auth.dto.SignUpRequest;
import hairSalonReservation.sideProject.domain.auth.dto.SignUpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
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