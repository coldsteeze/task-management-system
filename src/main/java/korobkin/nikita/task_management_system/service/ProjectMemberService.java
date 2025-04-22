package korobkin.nikita.task_management_system.service;


import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.entity.User;

public interface ProjectMemberService {

    void addProjectMember(AddProjectMemberRequest addProjectMemberRequest, User currentUser);
}
