package korobkin.nikita.task_management_system.mapper;

import korobkin.nikita.task_management_system.dto.request.CreateTaskRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskRequest;
import korobkin.nikita.task_management_system.dto.response.TaskResponse;
import korobkin.nikita.task_management_system.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "task.assignee.username", target = "assigneeName")
    @Mapping(source = "task.board.name", target = "boardName")
    TaskResponse toDto(Task task);

    Task toEntity(CreateTaskRequest createTaskRequest);

    void updateEntityFromDto(@MappingTarget Task task, UpdateTaskRequest updateTaskRequest);
}
