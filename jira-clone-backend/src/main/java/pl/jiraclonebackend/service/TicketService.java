package pl.jiraclonebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.util.Status;
import pl.jiraclonebackend.entity.Ticket;
import pl.jiraclonebackend.repository.TicketRepository;

import java.util.List;

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

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findTicketByStatus(Status status) {
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> findTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }

    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        long idTicketToUpdate = ticket.getId();
        Ticket ticketToUpdate = findTicketById(idTicketToUpdate);

        ticketToUpdate.setTitle(ticket.getTitle());
        ticketToUpdate.setDescription(ticket.getDescription());
        ticketToUpdate.setStatus(ticket.getStatus());
        ticketToUpdate.setFinished(ticket.isFinished());

        return ticketRepository.save(ticketToUpdate);
    }


}
