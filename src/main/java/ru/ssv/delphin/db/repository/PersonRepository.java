package ru.ssv.delphin.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssv.delphin.db.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
