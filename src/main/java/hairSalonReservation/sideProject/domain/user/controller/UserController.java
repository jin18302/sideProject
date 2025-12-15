package hairSalonReservation.sideProject.domain.user.controller;

import hairSalonReservation.sideProject.domain.user.dto.request.DeleteUserRequest;
import hairSalonReservation.sideProject.domain.user.dto.request.UpdateUserInfoRequest;
import hairSalonReservation.sideProject.domain.user.dto.response.UserResponse;
import hairSalonReservation.sideProject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> readUserinfo(@RequestAttribute("userId") Long userId) {

        UserResponse response = userService.readUserInfo(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping
    public ResponseEntity<UserResponse> updateUserInfo(
            @RequestAttribute("userId") Long userId,
            @RequestBody UpdateUserInfoRequest request
    ) {
        UserResponse response = userService.updateUserInfo(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
            @RequestAttribute("userId") Long userId,
            @RequestBody DeleteUserRequest request
    ) {
        userService.deleteUser(userId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
