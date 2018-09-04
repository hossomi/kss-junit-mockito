package hossomi.kss.junitmockito.account;

import hossomi.kss.junitmockito.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private UserService users;
    private AccountClient accounts;

    @Autowired
    public AccountService(UserService users, AccountClient payments) {
        this.users = users;
        this.accounts = payments;
    }

    public Optional<Account> addToUserAccount(Long userId, double value) {
        return users.get(userId)
                .map(user -> accounts.add(user.getAccountId(), value));
    }
}
