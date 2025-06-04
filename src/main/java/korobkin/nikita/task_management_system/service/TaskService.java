package korobkin.nikita.task_management_system.service;

import korobkin.nikita.task_management_system.dto.request.CreateTaskRequest;
import korobkin.nikita.task_management_system.dto.response.TaskResponse;
import korobkin.nikita.task_management_system.entity.User;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest createTaskRequest, User currentUser);
}
