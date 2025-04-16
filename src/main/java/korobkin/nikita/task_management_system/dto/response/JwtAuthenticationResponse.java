package korobkin.nikita.task_management_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtAuthenticationResponse {
    private String token;
}
