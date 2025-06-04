package korobkin.nikita.task_management_system.repository;

import korobkin.nikita.task_management_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
