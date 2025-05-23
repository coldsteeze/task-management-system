package korobkin.nikita.task_management_system.mapper;

import korobkin.nikita.task_management_system.dto.response.ProjectMemberResponse;
import korobkin.nikita.task_management_system.entity.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.email", target = "email")
    ProjectMemberResponse toDto(ProjectMember projectMember);

    List<ProjectMemberResponse> toDtoList(List<ProjectMember> projectMembers);
}
