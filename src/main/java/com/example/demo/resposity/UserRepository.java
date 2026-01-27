package com.example.demo.resposity;
import com.example.demo.model.User;
import com.example.demo.model.Role;
import java.util.List;
import com.example.demo.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
    List<User> findByStatus(UserStatus status);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByEmail(String email);
}