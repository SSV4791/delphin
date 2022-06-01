package ru.ssv.delphin.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssv.delphin.db.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
