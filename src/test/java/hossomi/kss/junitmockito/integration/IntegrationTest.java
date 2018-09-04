package hossomi.kss.junitmockito.integration;

import hossomi.kss.junitmockito.account.Account;
import hossomi.kss.junitmockito.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class IntegrationTest {

    private Client client;

    @Before
    public void setup() {
        client = new Client(8080);
    }

    @Test
    public void testAccountManagement() {
        final String ACCOUNT_ID = "ACC0123";

        User user = User.builder()
                .name("Hossomi")
                .age(10)
                .accountId(ACCOUNT_ID)
                .build();

        // Create user

        ResponseEntity<User> userResponse = client.createUser(user);
        assertThat(userResponse.getStatusCode()).isEqualTo(OK);
        assertThat(userResponse.getBody()).isNotNull();
        user = userResponse.getBody();

        // Get user

        userResponse = client.getUser(user.getId());
        assertThat(userResponse.getStatusCode()).isEqualTo(OK);
        assertThat(userResponse.getBody()).isEqualTo(user);

        // Get account

        ResponseEntity<Account> accountResponse = client.getUserAccount(user.getId());
        assertThat(accountResponse.getStatusCode()).isEqualTo(OK);
        assertThat(accountResponse.getBody()).isNotNull();

        Account account = accountResponse.getBody();
        assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(account.getBalance()).isEqualTo(0.0);

        // Add credit

        accountResponse = client.addCreditToUserAccount(user.getId(), 123.0);
        assertThat(accountResponse.getStatusCode()).isEqualTo(OK);
        assertThat(accountResponse.getBody()).isNotNull();

        account = accountResponse.getBody();
        assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(account.getBalance()).isEqualTo(123.0);

        // Add more credit

        accountResponse = client.addCreditToUserAccount(user.getId(), 543.0);
        assertThat(accountResponse.getStatusCode()).isEqualTo(OK);
        assertThat(accountResponse.getBody()).isNotNull();

        account = accountResponse.getBody();
        assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(account.getBalance()).isEqualTo(666.0);
    }

    @Test
    public void testUnknownAccount() {
        final long USER_ID = 12L;

        ResponseEntity<Account> accountResponse = client.getUserAccount(USER_ID);
        assertThat(accountResponse.getStatusCode()).isEqualTo(NOT_FOUND);

        accountResponse = client.addCreditToUserAccount(USER_ID, 123.0);
        assertThat(accountResponse.getStatusCode()).isEqualTo(NOT_FOUND);
    }
}
