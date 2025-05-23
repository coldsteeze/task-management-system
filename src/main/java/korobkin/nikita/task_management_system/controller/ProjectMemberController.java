package korobkin.nikita.task_management_system.controller;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.dto.request.UpdateProjectMemberRoleRequest;
import korobkin.nikita.task_management_system.dto.response.ProjectMemberResponse;
import korobkin.nikita.task_management_system.security.UserDetailsImpl;
import korobkin.nikita.task_management_system.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project-members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<Void> addProjectMember(
            @RequestBody @Valid AddProjectMemberRequest addProjectMemberRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectMemberService.addProjectMember(addProjectMemberRequest, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProjectMemberResponse>> getProjectMembers(
            @RequestParam Long projectId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userDetails.getUser()));
    }

    @PutMapping
    public ResponseEntity<Void> updateProjectMember(
            @RequestBody @Valid UpdateProjectMemberRoleRequest updateProjectMemberRoleRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectMemberService.updateProjectMember(updateProjectMemberRoleRequest, userDetails.getUser());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{projectId}/{userId}")
    public ResponseEntity<Void> deleteProjectMember(
            @PathVariable Long projectId,
            @PathVariable Long userId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectMemberService.deleteProjectMember(projectId, userId, userDetails.getUser());

        return ResponseEntity.noContent().build();
    }
}
