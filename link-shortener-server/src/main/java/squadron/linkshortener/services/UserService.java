package squadron.linkshortener.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import squadron.linkshortener.models.User;
import squadron.linkshortener.repos.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(String username, String password, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        return userRepository.save(user);
    }

}
