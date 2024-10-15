package squadron.linkshortener.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import squadron.linkshortener.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}