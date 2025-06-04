package korobkin.nikita.task_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import korobkin.nikita.task_management_system.entity.enums.StatusTask;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be between 0 and 100 characters")
    private String title;

    @Size(max = 255, message = "Description must be between 0 and 255 characters")
    private String description;

    @NotNull(message = "Status task is required")
    private StatusTask status;

    @Positive(message = "Board id must be positive")
    @NotNull(message = "Board id is required")
    private Long boardId;

    private Long assigneeId;
}
