package com.example.plannerapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String description;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @ManyToOne(optional = false)
    private Priority priority;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;


}
