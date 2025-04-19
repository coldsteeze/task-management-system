package korobkin.nikita.task_management_system.mapper;

import korobkin.nikita.task_management_system.dto.request.CreateProjectRequest;
import korobkin.nikita.task_management_system.dto.response.ProjectResponse;
import korobkin.nikita.task_management_system.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toEntity(CreateProjectRequest createProjectRequest);

    List<ProjectResponse> toDtoList(Set<Project> projects);
}
