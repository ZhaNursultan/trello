package kz.bitlab.treello.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "taskcategories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
