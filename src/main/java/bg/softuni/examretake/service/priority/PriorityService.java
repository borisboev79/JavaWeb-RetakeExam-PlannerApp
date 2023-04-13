package bg.softuni.examretake.service.priority;

import bg.softuni.examretake.init.DatabaseInitialization;
import bg.softuni.examretake.model.entity.Priority;
import bg.softuni.examretake.model.enums.PriorityLevel;
import bg.softuni.examretake.repository.PriorityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityService implements DatabaseInitialization {
    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @PostConstruct
    @Override
    public void dbInit() {
        if (!isDbInit()) {
            List<Priority> priorities = (List.of(

                    Priority.builder().name(PriorityLevel.LOW).description("Should be fixed if time permits but can be postponed.").build(),
                    Priority.builder().name(PriorityLevel.IMPORTANT).description("A core functionality that your product is explicitly supposed to perform is compromised.").build(),
                    Priority.builder().name(PriorityLevel.URGENT).description("An urgent problem that blocks the system use until the issue is resolved.").build()
            )
            );
            this.priorityRepository.saveAllAndFlush(priorities);

        }
    }

    @Override
    public boolean isDbInit() {
        return this.priorityRepository.count() > 0;
    }
}
