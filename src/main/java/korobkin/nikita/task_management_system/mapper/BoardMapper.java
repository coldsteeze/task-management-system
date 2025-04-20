package korobkin.nikita.task_management_system.mapper;

import korobkin.nikita.task_management_system.dto.request.CreateBoardRequest;
import korobkin.nikita.task_management_system.entity.Board;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board toEntity(CreateBoardRequest createBoardRequest);
}
