package korobkin.nikita.task_management_system.repository;

import korobkin.nikita.task_management_system.entity.Project;
import korobkin.nikita.task_management_system.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @EntityGraph(attributePaths = "owner")
    Set<Project> findAllByOwner(User owner);

    Boolean existsByOwnerAndName(User owner, String name);
}
