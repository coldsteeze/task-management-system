package korobkin.nikita.task_management_system.service.impl;

import korobkin.nikita.task_management_system.dto.request.LoginRequest;
import korobkin.nikita.task_management_system.dto.request.RegisterRequest;
import korobkin.nikita.task_management_system.dto.response.JwtAuthenticationResponse;
import korobkin.nikita.task_management_system.entity.User;
import korobkin.nikita.task_management_system.mapper.UserMapper;
import korobkin.nikita.task_management_system.repository.UserRepository;
import korobkin.nikita.task_management_system.security.JwtProvider;
import korobkin.nikita.task_management_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;


    @Override
    @Transactional
    public JwtAuthenticationResponse register(RegisterRequest registerRequest) {
        if (!userRepository.existsByEmail(registerRequest.getEmail()) &&
                !userRepository.existsByUsername(registerRequest.getUsername())) {

            User user = userMapper.toEntity(registerRequest);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return new JwtAuthenticationResponse(jwtProvider.generateToken(user.getUsername()));
        } else {
            throw new RuntimeException("Email or Username is already in use");
        }
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password");
        }

        return new JwtAuthenticationResponse(
                jwtProvider.generateToken(authenticationToken.getPrincipal().toString())
        );
    }
}
