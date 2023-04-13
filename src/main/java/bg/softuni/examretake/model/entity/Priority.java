package bg.softuni.examretake.model.entity;

import bg.softuni.examretake.model.enums.PriorityLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @NonNull
    private PriorityLevel name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority", targetEntity = Task.class)
    private Set<Task> tasks;

}
