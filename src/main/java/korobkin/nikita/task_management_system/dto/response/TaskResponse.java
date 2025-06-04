package korobkin.nikita.task_management_system.dto.response;

import korobkin.nikita.task_management_system.entity.enums.StatusTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskResponse {

    private String title;
    private String description;
    private StatusTask status;
    private String assigneeName;
    private String boardName;
}
