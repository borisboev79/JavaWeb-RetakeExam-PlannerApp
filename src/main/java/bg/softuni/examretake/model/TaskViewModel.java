package bg.softuni.examretake.model;

import bg.softuni.examretake.model.enums.PriorityLevel;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskViewModel {

    private Long id;

    private PriorityLevel priority;

    private LocalDate dueDate;

    private String description;

}
