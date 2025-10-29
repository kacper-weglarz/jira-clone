package pl.jiraclonebackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.jiraclonebackend.entity.Ticket;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.service.TicketService;
import pl.jiraclonebackend.util.Status;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    public Ticket addTicket(@Valid @RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @GetMapping
    public Page<Ticket> findAllTickets(Pageable pageable) {
        return ticketService.getAllTickets(pageable);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.findTicketById(id);
    }

    @GetMapping("/status/{status}")
    public Page<Ticket> findTicketsByStatus(@PathVariable Status status, Pageable pageable) {
        return ticketService.findTicketByStatus(status, pageable);
    }

    @GetMapping("/user/{userId}")
    public Page<Ticket> findTicketsByUser(@PathVariable Long userId, Pageable pageable) {
        return ticketService.findTicketsByUser(userId, pageable);
    }

    @GetMapping("/created-after")
    public Page<Ticket> findTicketsByCreatedAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                                  Pageable pageable) {
        return ticketService.findTicketsByCreatedAfter(date, pageable);
    }

    @GetMapping("/created-between")
    public Page<Ticket> findTicketsByCreatedBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                                    Pageable pageable) {
        return ticketService.findByCreatedAtBetween(start, end, pageable);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        ticket.setId(id);
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.removeTicketById(id);
    }
}
