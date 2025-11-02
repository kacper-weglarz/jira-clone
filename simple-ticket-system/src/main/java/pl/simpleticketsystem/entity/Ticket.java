package pl.simpleticketsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.simpleticketsystem.util.Priority;
import pl.simpleticketsystem.util.Status;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.NONE)
    private Status status = Status.NEW;

    public void setStatus(Status status) {
        this.status = status;
        if (status == Status.DONE && this.finishedAt == null) {
            this.finishedAt = LocalDateTime.now();
        }
    }

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedTo;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
