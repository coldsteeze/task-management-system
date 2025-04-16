package korobkin.nikita.task_management_system.mapper;

import korobkin.nikita.task_management_system.dto.request.RegisterRequest;
import korobkin.nikita.task_management_system.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest registerRequest);
}
