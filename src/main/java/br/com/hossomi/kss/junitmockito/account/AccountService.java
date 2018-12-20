package br.com.hossomi.kss.junitmockito.account;

import br.com.hossomi.kss.junitmockito.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    private UserService users;
    private AccountClient accounts;

    @Autowired
    public AccountService(UserService users, AccountClient payments) {
        this.users = users;
        this.accounts = payments;
    }

    public Optional<Account> addCreditToUserAccount(Long userId, double value) {
        log.info("Add [{}] to user [{}] account", value, userId);
        return users.get(userId)
                .map(user -> accounts.addCredit(user.getAccountId(), value));
    }

    public Optional<Account> getUserAccount(Long userId) {
        log.info("Get user [{}] account", userId);
        return users.get(userId)
                .map(user -> accounts.getAccount(user.getAccountId()));
    }
}
