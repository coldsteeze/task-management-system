package korobkin.nikita.task_management_system.service.impl;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.CreateProjectRequest;
import korobkin.nikita.task_management_system.dto.response.ProjectResponse;
import korobkin.nikita.task_management_system.entity.Project;
import korobkin.nikita.task_management_system.entity.User;
import korobkin.nikita.task_management_system.mapper.ProjectMapper;
import korobkin.nikita.task_management_system.repository.ProjectRepository;
import korobkin.nikita.task_management_system.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public ProjectResponse createProject(@Valid CreateProjectRequest request, User currentUser) {
        if (projectRepository.existsByOwnerAndName(currentUser, request.getName())) {
            throw new RuntimeException("A project with this name already exists for the user.");
        }

        Project project = projectMapper.toEntity(request);
        project.setOwner(currentUser);
        projectRepository.save(project);

        return new ProjectResponse(project.getId(), project.getName(), project.getDescription());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getAllProjectsForUser(User currentUser) {
        return projectMapper.toDtoList(projectRepository.findAllByOwner(currentUser));
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId, User currentUser) {
        Project project = projectRepository.findById(projectId).
                orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getOwner().equals(currentUser) && currentUser.getProjects().contains(project)) {
            throw new RuntimeException("You are not allowed to delete this project");
        }

        projectRepository.delete(project);
    }
}
