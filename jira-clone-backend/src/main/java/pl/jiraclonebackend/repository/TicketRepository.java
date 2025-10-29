package pl.jiraclonebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.util.Status;
import pl.jiraclonebackend.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>{

    public List<Ticket> findByStatus(Status status);

    public List<Ticket> findByUser(User user);
}
