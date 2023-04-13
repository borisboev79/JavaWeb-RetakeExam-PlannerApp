package bg.softuni.examretake.repository;

import bg.softuni.examretake.model.entity.Priority;
import bg.softuni.examretake.model.enums.PriorityLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
   Optional<Priority> findByName(PriorityLevel priority);
}

