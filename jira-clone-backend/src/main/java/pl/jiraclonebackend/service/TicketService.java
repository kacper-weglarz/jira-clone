package pl.jiraclonebackend.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.util.Status;
import pl.jiraclonebackend.entity.Ticket;
import pl.jiraclonebackend.repository.TicketRepository;
import java.time.LocalDateTime;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void removeTicketById(long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket findTicketById(long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found" + id));
    }

    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    public Page<Ticket> findTicketByStatus(Status status, Pageable pageable) {
        return ticketRepository.findByStatus(status, pageable);
    }

    public Page<Ticket> findTicketsByUser(Long userId, Pageable pageable) {
        return ticketRepository.findByAssignedTo(userId, pageable);
    }

    public Page<Ticket> findTicketsByCreatedAfter(LocalDateTime createdAfter, Pageable pageable) {
        return ticketRepository.findByCreatedAtAfter(createdAfter, pageable);
    }

    public Page<Ticket> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return ticketRepository.findByCreatedAtBetween(start, end, pageable);
    }


    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        Long idTicketToUpdate = ticket.getId();
        if (ticket.getId() == null || ticket.getId() <= 0) {
            throw new RuntimeException("Ticket id not found" + idTicketToUpdate);
        }

        Ticket ticketToUpdate = findTicketById(idTicketToUpdate);

        if (ticket.getTitle() != null) {
            ticketToUpdate.setTitle(ticket.getTitle());
        }

        if (ticket.getDescription() != null) {
            ticketToUpdate.setDescription(ticket.getDescription());
        }

        if (ticket.getStatus() != null) {
            ticketToUpdate.setStatus(ticket.getStatus());
        }

        if (ticket.getPriority() != null) {
            ticketToUpdate.setPriority(ticket.getPriority());
        }

        if (ticket.getAssignedTo() != null) {
            ticketToUpdate.setAssignedTo(ticket.getAssignedTo());
        }

        return ticketRepository.save(ticketToUpdate);
    }


}
