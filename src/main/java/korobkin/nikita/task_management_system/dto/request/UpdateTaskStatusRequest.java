package korobkin.nikita.task_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import korobkin.nikita.task_management_system.entity.enums.StatusTask;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskStatusRequest {

    @NotNull(message = "Status task is required")
    private StatusTask status;
}
