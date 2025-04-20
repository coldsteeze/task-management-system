package korobkin.nikita.task_management_system.mapper;

import korobkin.nikita.task_management_system.dto.request.CreateBoardRequest;
import korobkin.nikita.task_management_system.dto.response.BoardResponse;
import korobkin.nikita.task_management_system.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board toEntity(CreateBoardRequest createBoardRequest);

    @Mapping(source = "project.id", target = "projectId")
    BoardResponse toDto(Board board);

    List<BoardResponse> toDtoList(Set<Board> boards);
}
