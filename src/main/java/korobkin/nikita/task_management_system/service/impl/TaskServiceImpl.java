package korobkin.nikita.task_management_system.service.impl;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.CreateTaskRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskAssigneeRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateTaskStatusRequest;
import korobkin.nikita.task_management_system.dto.response.TaskResponse;
import korobkin.nikita.task_management_system.entity.*;
import korobkin.nikita.task_management_system.entity.enums.ProjectRole;
import korobkin.nikita.task_management_system.mapper.TaskMapper;
import korobkin.nikita.task_management_system.repository.BoardRepository;
import korobkin.nikita.task_management_system.repository.ProjectMemberRepository;
import korobkin.nikita.task_management_system.repository.TaskRepository;
import korobkin.nikita.task_management_system.repository.UserRepository;
import korobkin.nikita.task_management_system.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskResponse createTask(@Valid CreateTaskRequest createTaskRequest, User currentUser) {
        Board board = boardRepository.findById(createTaskRequest.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        Project project = board.getProject();

        checkPermission(project, currentUser);

        Task task = taskMapper.toEntity(createTaskRequest);
        task.setBoard(board);
        task.setAssignee(validateAndGetAssignee(createTaskRequest.getAssigneeId(), project));

        taskRepository.save(task);

        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(@Valid UpdateTaskRequest request, Long taskId, User currentUser) {
        Task task = getTaskWithPermissionCheck(taskId, currentUser);

        taskMapper.updateEntityFromDto(task, request);

        User assignee = validateAndGetAssignee(request.getAssigneeId(), task.getBoard().getProject());
        task.setAssignee(assignee);

        taskRepository.save(task);

        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskStatus(UpdateTaskStatusRequest request, Long taskId, User currentUser) {
        Task task = getTaskWithPermissionCheck(taskId, currentUser);

        task.setStatus(request.getStatus());
        taskRepository.save(task);

        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskAssignee(UpdateTaskAssigneeRequest request, Long taskId, User currentUser) {
        Task task = getTaskWithPermissionCheck(taskId, currentUser);

        task.setAssignee(validateAndGetAssignee(request.getAssigneeId(), task.getBoard().getProject()));
        taskRepository.save(task);

        return taskMapper.toDto(task);
    }

    private User validateAndGetAssignee(Long assigneeId, Project project) {
        if (assigneeId == null) {
            return null;
        }

        User user = userRepository.findById(assigneeId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!projectMemberRepository.existsByProjectIdAndUserId(project.getId(), user.getId())) {
            throw new IllegalArgumentException("Assignee is not a member of the project");
        }

        return user;
    }

    private void checkPermission(Project project, User currentUser) {
        ProjectMember projectMember = projectMemberRepository.findByProjectIdAndUserId(
                        project.getId(),
                        currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("You are not a member of the project"));

        if (projectMember.getProjectRole() == ProjectRole.VIEWER) {
            throw new IllegalArgumentException("You don't have permission to update tasks");
        }
    }

    private Task getTaskWithPermissionCheck(Long taskId, User currentUser) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Project project = task.getBoard().getProject();
        checkPermission(project, currentUser);

        return task;
    }
}
