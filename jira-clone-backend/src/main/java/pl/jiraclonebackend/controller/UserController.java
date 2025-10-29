package pl.jiraclonebackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/get/{id}")
    public User getUserByid(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
    }
}
