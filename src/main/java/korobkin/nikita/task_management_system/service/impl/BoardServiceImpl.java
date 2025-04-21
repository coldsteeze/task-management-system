package korobkin.nikita.task_management_system.service.impl;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.CreateBoardRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateBoardRequest;
import korobkin.nikita.task_management_system.dto.response.BoardResponse;
import korobkin.nikita.task_management_system.entity.Board;
import korobkin.nikita.task_management_system.entity.Project;
import korobkin.nikita.task_management_system.entity.User;
import korobkin.nikita.task_management_system.mapper.BoardMapper;
import korobkin.nikita.task_management_system.repository.BoardRepository;
import korobkin.nikita.task_management_system.repository.ProjectRepository;
import korobkin.nikita.task_management_system.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ProjectRepository projectRepository;
    private final BoardMapper boardMapper;

    @Override
    @Transactional
    public BoardResponse createBoard(@Valid CreateBoardRequest createBoardRequest, User currentUser) {
        if (!projectRepository.existsByIdAndOwner(createBoardRequest.getProjectId(), currentUser)) {
            throw new RuntimeException("Project not found or user is not the owner");
        }

        Project project = projectRepository.findById(createBoardRequest.getProjectId()).get();

        if (boardRepository.existsByNameAndProject(createBoardRequest.getName(), project)) {
            throw new RuntimeException("There is already a board with this name");
        }

        Board board = boardMapper.toEntity(createBoardRequest);
        board.setProject(project);
        boardRepository.save(board);

        return boardMapper.toDto(board);
    }

    @Override
    public List<BoardResponse> getBoardsByProject(Long projectId, User currentUser) {
        if (!projectRepository.existsByIdAndOwner(projectId, currentUser)) {
            throw new RuntimeException("Project not found or user is not the owner");
        }

        Project project = projectRepository.findById(projectId).get();

        return boardMapper.toDtoList(boardRepository.findAllByProject(project));
    }

    @Override
    @Transactional
    public BoardResponse updateBoard(UpdateBoardRequest updateBoardRequest, User currentUser) {
        Board board = boardRepository.findById(updateBoardRequest.getBoardId())
                .orElseThrow(() -> new RuntimeException("Board not found"));

        if (!board.getProject().getOwner().equals(currentUser)) {
            throw new RuntimeException("User is not the owner of the project");
        }

        if (boardRepository.existsByNameAndProject(updateBoardRequest.getName(), board.getProject())
                && !updateBoardRequest.getName().equals(board.getName())) {
            throw new RuntimeException("There is already a board with this name");
        }

        boardMapper.updateEntityFromDto(board, updateBoardRequest);
        boardRepository.save(board);

        return boardMapper.toDto(board);
    }
}
