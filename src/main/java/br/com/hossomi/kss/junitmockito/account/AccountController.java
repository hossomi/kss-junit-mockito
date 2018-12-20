package br.com.hossomi.kss.junitmockito.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        return accounts.addCreditToUserAccount(userId, value)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @GetMapping
    public ResponseEntity<Account> get(
            @PathVariable("userId") Long userId) {

        return accounts.getUserAccount(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }
}
