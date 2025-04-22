package korobkin.nikita.task_management_system.controller;

import jakarta.validation.Valid;
import korobkin.nikita.task_management_system.dto.request.AddProjectMemberRequest;
import korobkin.nikita.task_management_system.security.UserDetailsImpl;
import korobkin.nikita.task_management_system.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
