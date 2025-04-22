package korobkin.nikita.task_management_system.service.impl;

import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.entity.User;
import korobkin.nikita.task_management_system.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Override
    public void addProjectMember(AddProjectMemberRequest addProjectMemberRequest, User currentUser) {

    }
}
