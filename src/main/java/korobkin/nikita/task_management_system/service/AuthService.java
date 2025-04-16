package korobkin.nikita.task_management_system.service;

import korobkin.nikita.task_management_system.dto.request.LoginRequest;
import korobkin.nikita.task_management_system.dto.request.RegisterRequest;
import korobkin.nikita.task_management_system.dto.response.JwtAuthenticationResponse;

public interface AuthService {

    JwtAuthenticationResponse register(RegisterRequest registerRequest);

    JwtAuthenticationResponse login(LoginRequest loginRequest);
}
