package korobkin.nikita.task_management_system.service;

import korobkin.nikita.task_management_system.dto.request.CreateBoardRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateBoardRequest;
import korobkin.nikita.task_management_system.dto.response.BoardResponse;
import korobkin.nikita.task_management_system.entity.User;

import java.util.List;

public interface BoardService {

    BoardResponse createBoard(CreateBoardRequest createBoardRequest, User currentUser);

    List<BoardResponse> getBoardsByProject(Long projectId, User currentUser);

    BoardResponse getBoardById(Long boardId, User currentUser);

    BoardResponse updateBoard(UpdateBoardRequest updateBoardRequest, User currentUser);

    void deleteBoard(Long boardId, User currentUser);
}
