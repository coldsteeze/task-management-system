package korobkin.nikita.task_management_system.dto.response;

import korobkin.nikita.task_management_system.entity.enums.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectMemberResponse {

    private Long userId;
    private String username;
    private String email;
    private ProjectRole projectRole;
}
