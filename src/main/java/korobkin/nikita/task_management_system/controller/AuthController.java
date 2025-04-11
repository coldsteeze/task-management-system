package korobkin.nikita.task_management_system.controller;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.LoginRequest;
import korobkin.nikita.task_management_system.dto.request.RegisterRequest;
import korobkin.nikita.task_management_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login());
    }
}
