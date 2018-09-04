package hossomi.kss.junitmockito.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class AccountClient {

    private AccountRepository accounts;

    @Autowired
    public AccountClient(AccountRepository accounts) {
        this.accounts = accounts;
    }

    @Transactional
    public Account addCredit(String accountId, double value) {
        try {
            log.info("Adding [{}] to account [{}]", value, accountId);
            // Simulate network delay
            Thread.sleep(500);

            Account account = accounts.findById(accountId)
                    .orElseGet(() -> new Account(accountId, 0.0));
            account.setBalance(account.getBalance() + value);

            accounts.save(account);
            return account;
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Optional<Account> getAccount(String accountId) {
        try {
            log.info("Getting account [{}] balance", accountId);
            // Simulate network delay
            Thread.sleep(500);

            return accounts.findById(accountId);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
