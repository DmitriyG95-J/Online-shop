package online.shop.repository;

import online.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
    Optional<User> findByEmail(String email);
}
