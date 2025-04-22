package korobkin.nikita.task_management_system.repository;

import korobkin.nikita.task_management_system.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    Boolean existsByProjectIdAndUserId(Long projectId, Long userId);
}
