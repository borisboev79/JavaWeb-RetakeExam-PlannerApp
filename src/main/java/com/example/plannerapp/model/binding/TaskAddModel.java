package com.example.plannerapp.model.binding;

import com.example.plannerapp.model.enums.PriorityLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskAddModel {

    @NotNull
    @Size(min = 2, max = 50)
    private String description;

    @NotNull
    @Future
    private LocalDate dueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PriorityLevel priority;
}
