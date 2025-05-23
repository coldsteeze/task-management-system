package korobkin.nikita.task_management_system.service;


import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateProjectMemberRoleRequest;
import korobkin.nikita.task_management_system.dto.response.ProjectMemberResponse;
import korobkin.nikita.task_management_system.entity.User;

import java.util.List;

public interface ProjectMemberService {

    void addProjectMember(AddProjectMemberRequest addProjectMemberRequest, User currentUser);

    void deleteProjectMember(Long projectId, Long userId, User currentUser);

    void updateProjectMember(UpdateProjectMemberRoleRequest updateProjectMemberRoleRequest, User currentUser);

    List<ProjectMemberResponse> getProjectMembers(Long projectId, User currentUser);
}
