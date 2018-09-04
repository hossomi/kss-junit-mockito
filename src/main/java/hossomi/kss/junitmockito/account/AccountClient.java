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
    public Account addCredit(String accountId, double value) {
        log.info("[ACCOUNTS] >> POST /accounts/{} : {}", accountId, value);

        Account account = accounts.findById(accountId)
                .orElseGet(() -> new Account(accountId, 0.0));
        account.setBalance(account.getBalance() + value);

        accounts.save(account);

        log.info("[ACCOUNTS] << [200] POST /accounts/{} : {}", accountId, account);
        return account;
    }

    @Transactional
    public Account getAccount(String accountId) {
        log.info("[ACCOUNTS] >> GET /accounts/{}", accountId);

        Account account = accounts.findById(accountId)
                .orElseGet(() -> new Account(accountId, 0.0));

        log.info("[ACCOUNTS] << GET /accounts/{} : {}", accountId, account);
        return account;
    }
}
