package korobkin.nikita.task_management_system.controller;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.CreateBoardRequest;
import korobkin.nikita.task_management_system.dto.response.BoardResponse;
import korobkin.nikita.task_management_system.security.UserDetailsImpl;
import korobkin.nikita.task_management_system.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(
            @RequestBody @Valid CreateBoardRequest createBoardRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(boardService.createBoard(createBoardRequest, userDetails.getUser()));
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getBoardsByProject(
            @RequestParam Long projectId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(boardService.getBoardsByProject(projectId, userDetails.getUser()));
    }
}
