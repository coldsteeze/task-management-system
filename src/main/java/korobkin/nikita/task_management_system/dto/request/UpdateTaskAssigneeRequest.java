package korobkin.nikita.task_management_system.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskAssigneeRequest {

    @Positive(message = "Assignee id must be positive")
    private Long assigneeId;
}
