package pl.jiraclonebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import pl.jiraclonebackend.util.Status;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean finished;

    @ManyToOne
    private User user;
}
