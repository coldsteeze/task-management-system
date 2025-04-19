package korobkin.nikita.task_management_system.service;

import korobkin.nikita.task_management_system.dto.request.CreateProjectRequest;
import korobkin.nikita.task_management_system.dto.response.ProjectResponse;
import korobkin.nikita.task_management_system.entity.User;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(CreateProjectRequest request, User currentUser);

    List<ProjectResponse> getAllProjectsForUser(User currentUser);

    void deleteProject(Long projectId, User currentUser);
}
