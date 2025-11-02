package pl.jiraclonebackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.service.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping()
    public Page<User> findAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public User getUserByid(@PathVariable Long id) {
        return userService.findUserById(id);
    }


    @GetMapping("/created-after")
    public Page<User> getUsersByCreatedAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                             LocalDateTime createdAfter, Pageable pageable) {
        return userService.getUsersByCreatedAtAfter(createdAfter, pageable);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
    }
}
