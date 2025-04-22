package korobkin.nikita.task_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import korobkin.nikita.task_management_system.entity.enums.ProjectRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProjectMemberRequest {

    @Positive(message = "User ID should not be negative")
    @NotNull(message = "User id is required")
    private Long userId;

    @Positive(message = "User ID should not be negative")
    @NotNull(message = "User id is required")
    private Long projectId;

    @NotNull(message = "Project role is required")
    private ProjectRole projectRole;
}
