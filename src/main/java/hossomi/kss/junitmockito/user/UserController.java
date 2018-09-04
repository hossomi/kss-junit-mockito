package hossomi.kss.junitmockito.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService users;

    @Autowired
    public UserController(UserService users) {
        this.users = users;
    }

    @PostMapping
    public ResponseEntity<User> create(
            @RequestBody User user) {

        user = users.create(user);
        return ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(
            @PathVariable("userId") Long userId) {

        return users.get(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }
}
