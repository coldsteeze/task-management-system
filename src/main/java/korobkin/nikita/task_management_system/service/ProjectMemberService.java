package korobkin.nikita.task_management_system.service;


import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateProjectMemberRoleRequest;
import korobkin.nikita.task_management_system.entity.User;

public interface ProjectMemberService {

    void addProjectMember(AddProjectMemberRequest addProjectMemberRequest, User currentUser);

    void deleteProjectMember(Long projectId, Long userId, User currentUser);

    void updateProjectMember(UpdateProjectMemberRoleRequest updateProjectMemberRoleRequest, User currentUser);
}
