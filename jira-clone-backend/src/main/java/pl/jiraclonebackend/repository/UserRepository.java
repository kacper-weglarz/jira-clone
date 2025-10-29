package pl.jiraclonebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jiraclonebackend.entity.User;
import pl.jiraclonebackend.util.Role;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Page<User> findAllByRole(Role role, Pageable pageable);

    public Page<User> findUsersByCreatedAtAfter(LocalDateTime createdAtAfter, Pageable pageable);

}
