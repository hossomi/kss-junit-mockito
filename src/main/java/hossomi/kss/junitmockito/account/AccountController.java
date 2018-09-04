package hossomi.kss.junitmockito.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.notFound;

@Controller
@RequestMapping("/users/{userId}/account")
public class AccountController {

    private AccountService accounts;

    public AccountController(AccountService accounts) {
        this.accounts = accounts;
    }

    @PostMapping
    public ResponseEntity<Account> add(
            @PathVariable("userId") Long userId,
            @RequestBody Double value) {

        return accounts.addToUserAccount(userId, value)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }
}
