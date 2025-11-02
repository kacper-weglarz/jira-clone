package pl.jiraclonebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jiraclonebackend.entity.User;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Page<User> findUsersByCreatedAtAfter(LocalDateTime createdAtAfter, Pageable pageable);

    public User findByEmail(String email);
}
