package korobkin.nikita.task_management_system.service;

import korobkin.nikita.task_management_system.dto.request.CreateTaskRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskAssigneeRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskStatusRequest;
import korobkin.nikita.task_management_system.dto.response.TaskResponse;
import korobkin.nikita.task_management_system.entity.User;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest createTaskRequest, User currentUser);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, Long taskId, User currentUser);

    TaskResponse updateTaskStatus(UpdateTaskStatusRequest request, Long taskId, User currentUser);

    TaskResponse updateTaskAssignee(UpdateTaskAssigneeRequest request, Long taskId, User currentUser);

    void deleteTask(Long taskId, User currentUser);
}
