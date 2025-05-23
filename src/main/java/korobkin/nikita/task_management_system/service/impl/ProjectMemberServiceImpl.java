package korobkin.nikita.task_management_system.service.impl;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateProjectMemberRoleRequest;
import korobkin.nikita.task_management_system.entity.Project;
import korobkin.nikita.task_management_system.entity.ProjectMember;
import korobkin.nikita.task_management_system.entity.User;
import korobkin.nikita.task_management_system.entity.enums.ProjectRole;
import korobkin.nikita.task_management_system.repository.ProjectMemberRepository;
import korobkin.nikita.task_management_system.repository.ProjectRepository;
import korobkin.nikita.task_management_system.repository.UserRepository;
import korobkin.nikita.task_management_system.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addProjectMember(@Valid AddProjectMemberRequest request, User currentUser) {
        Project project = getOwnedProjectOrThrow(request.getProjectId(), currentUser);
        User user = getUserOrThrow(request.getUserId());

        if (projectMemberRepository.existsByProjectIdAndUserId(project.getId(), user.getId())) {
            throw new IllegalArgumentException("User already member of this project");
        }

        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(user);
        projectMember.setProjectRole(request.getProjectRole());

        projectMemberRepository.save(projectMember);
    }

    @Override
    @Transactional
    public void deleteProjectMember(Long projectId, Long userId, User currentUser) {
        Project project = getOwnedProjectOrThrow(projectId, currentUser);
        User user = getUserOrThrow(userId);
        ProjectMember projectMember = getProjectMemberOrThrow(project.getId(), user.getId());

        projectMemberRepository.delete(projectMember);
    }

    @Override
    @Transactional
    public void updateProjectMember(@Valid UpdateProjectMemberRoleRequest request, User currentUser) {
        Project project = getOwnedProjectOrThrow(request.getProjectId(), currentUser);
        User user = getUserOrThrow(request.getUserId());
        ProjectMember projectMember = getProjectMemberOrThrow(project.getId(), user.getId());

        projectMember.setProjectRole(request.getProjectRole());
    }

    private Project getOwnedProjectOrThrow(Long projectId, User currentUser) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        if (!project.getOwner().equals(currentUser)) {
            throw new IllegalArgumentException("You are not the owner of this project");
        }

        return project;
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private ProjectMember getProjectMemberOrThrow(Long projectId, Long userId) {
        return projectMemberRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new IllegalArgumentException("User is not a member of this project"));
    }
}
