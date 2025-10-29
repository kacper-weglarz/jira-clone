package pl.jiraclonebackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jiraclonebackend.entity.Ticket;
import pl.jiraclonebackend.service.TicketService;

@CrossOrigin
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/add")
    public Ticket addTicket(@Valid @RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @GetMapping("/get/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.findTicketById(id);
    }

    @PutMapping("/update")
    public Ticket updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.removeTicketById(id);
    }
}
