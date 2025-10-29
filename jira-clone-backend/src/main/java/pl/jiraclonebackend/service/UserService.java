package pl.jiraclonebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.repository.UserRepository;

import java.util.List;

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

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(User user) {
        long idUserToUpdate = user.getId();
        User userToUpdate = findUserById(idUserToUpdate);

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());

        return userRepository.save(userToUpdate);
    }
}
