package korobkin.nikita.task_management_system.service;

import korobkin.nikita.task_management_system.dto.request.CreateBoardRequest;
import korobkin.nikita.task_management_system.dto.response.BoardResponse;
import korobkin.nikita.task_management_system.entity.User;

public interface BoardService {

    BoardResponse createBoard(CreateBoardRequest createBoardRequest, User currentUser);
}
