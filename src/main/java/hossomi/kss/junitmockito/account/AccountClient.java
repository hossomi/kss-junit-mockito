package hossomi.kss.junitmockito.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class AccountClient {

    private AccountRepository accounts;

    @Autowired
    public AccountClient(AccountRepository accounts) {
        this.accounts = accounts;
    }

    @Transactional
    public Account add(String accountId, double value) {
        try {
            log.info("Adding [{}] to account [{}]", value, accountId);

            Account account = accounts.findById(accountId)
                    .orElseGet(() -> new Account(accountId, 0.0));
            account.setBalance(account.getBalance() + value);
            accounts.save(account);

            // Simulate network delay
            Thread.sleep(500);

            return account;
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
