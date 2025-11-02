package pl.simpleticketsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.simpleticketsystem.entity.Ticket;
import pl.simpleticketsystem.entity.User;
import pl.simpleticketsystem.service.TicketService;
import pl.simpleticketsystem.service.UserService;
import pl.simpleticketsystem.util.Priority;

@Controller
public class ViewController {

    private final TicketService ticketService;

    private final UserService userService;

    @Autowired
    public ViewController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping("/tickets")
    public String getTickets(Model model, Pageable pageable) {
        model.addAttribute("tickets",ticketService.getAllTickets(pageable));
        return "tickets";
    }

    @GetMapping("/add-ticket")
    public String showAddTicketForm(Model model, Pageable pageable) {
        model.addAttribute("users", userService.getAllUsers(pageable));
        return "add-ticket";
    }

    @GetMapping("/edit-ticket/{id}")
    public String showEditTicketForm(@PathVariable Long id, Model model, Pageable pageable) {
        Ticket ticket = ticketService.findTicketById(id);
        model.addAttribute("ticket", ticket);
        model.addAttribute("users", userService.getAllUsers(pageable));
        return "edit-ticket";
    }

    @PostMapping("/add-ticket")
    public String addTicket(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String email,
                            @RequestParam String title,
                            @RequestParam String description,
                            @RequestParam Priority priority) {


        User user = userService.findOrCreateUser(firstName, lastName, email);

        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setPriority(priority);
        ticket.setAssignedTo(user);

        ticketService.addTicket(ticket);

        return "redirect:/tickets";
    }

    @PostMapping("/edit-ticket/{id}")
    public String updateTicket(@PathVariable Long id, @ModelAttribute Ticket ticket) {
        ticket.setId(id);
        ticketService.updateTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/delete-ticket/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.removeTicketById(id);
        return "redirect:/tickets";
    }

}
