package squadron.linkshortener.apis;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import squadron.linkshortener.models.User;
import squadron.linkshortener.services.UserService;

@RestController
@RequestMapping("/api/users/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(String username, String password, String email, String role) {
        User user = userService.createUser(username, password, email, role);
        return ResponseEntity.ok(user);
    }
}
