package korobkin.nikita.task_management_system.controller;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.CreateTaskRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskRequest;
import korobkin.nikita.task_management_system.dto.response.TaskResponse;
import korobkin.nikita.task_management_system.security.UserDetailsImpl;
import korobkin.nikita.task_management_system.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody @Valid CreateTaskRequest createTaskRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(taskService.createTask(createTaskRequest, userDetails.getUser()));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody UpdateTaskRequest updateTaskRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(taskService.updateTask(updateTaskRequest, taskId, userDetails.getUser()));
    }
}
