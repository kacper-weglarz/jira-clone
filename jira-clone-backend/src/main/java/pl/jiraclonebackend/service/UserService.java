package pl.jiraclonebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.repository.UserRepository;
import pl.jiraclonebackend.util.Role;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> getUsersByRole(Role role, Pageable pageable) {
        return userRepository.findAllByRole(role, pageable);
    }

    public Page<User> getUsersByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable) {
        return userRepository.findUsersByCreatedAtAfter(createdAt, pageable);
    }


    @Transactional
    public User updateUser(User user) {
        Long idUserToUpdate = user.getId();
        if (user.getId() == null || user.getId() <=0) {
            throw new RuntimeException("User with id " + idUserToUpdate + " not found");
        }
        User userToUpdate = findUserById(user.getId());

        if (user.getFirstName() != null) {
            userToUpdate.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            userToUpdate.setLastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }

        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }

        if (user.getRole() != null) {
            userToUpdate.setRole(user.getRole());
        }

        return userRepository.save(userToUpdate);
    }
}
