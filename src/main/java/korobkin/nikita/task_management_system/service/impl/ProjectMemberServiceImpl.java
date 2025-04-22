package korobkin.nikita.task_management_system.service.impl;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.entity.Project;
import korobkin.nikita.task_management_system.entity.ProjectMember;
import korobkin.nikita.task_management_system.entity.User;
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
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!project.getOwner().equals(currentUser)) {
            throw new IllegalArgumentException("You are not owner of this project");
        }

        if (projectMemberRepository.existsByProjectIdAndUserId(project.getId(), user.getId())) {
            throw new IllegalArgumentException("User already member of this project");
        }

        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(user);
        projectMemberRepository.save(projectMember);
    }
}
