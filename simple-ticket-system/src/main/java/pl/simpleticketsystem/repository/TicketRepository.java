package pl.simpleticketsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.simpleticketsystem.util.Status;
import pl.simpleticketsystem.entity.Ticket;
import java.time.LocalDateTime;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>{


    public Page<Ticket> findAll(Pageable pageable);

    public Page<Ticket> findByStatus(Status status, Pageable pageable);

    public Page<Ticket> findByAssignedTo(Long userId, Pageable pageable);

    public Page<Ticket> findByCreatedAtAfter(LocalDateTime createdAfter, Pageable pageable);

    public Page<Ticket> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}

