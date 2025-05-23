package korobkin.nikita.task_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import korobkin.nikita.task_management_system.entity.enums.ProjectRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProjectMemberRoleRequest {

    @Positive(message = "User id must be positive")
    @NotNull(message = "User id is required")
    private Long userId;

    @Positive(message = "Project id must be positive")
    @NotNull(message = "User id is required")
    private Long projectId;

    @NotNull(message = "Project role is required")
    private ProjectRole projectRole;
}
