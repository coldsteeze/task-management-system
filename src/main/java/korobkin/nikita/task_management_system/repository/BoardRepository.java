package korobkin.nikita.task_management_system.repository;

import korobkin.nikita.task_management_system.entity.Board;
import korobkin.nikita.task_management_system.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Boolean existsByNameAndProject(String name, Project project);
}
