package hossomi.kss.junitmockito.integration;

import hossomi.kss.junitmockito.Server;
import hossomi.kss.junitmockito.account.Account;
import hossomi.kss.junitmockito.account.AccountClient;
import hossomi.kss.junitmockito.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Server.class}, webEnvironment = RANDOM_PORT)
public class MockedIntegrationTest {

    @MockBean
    private AccountClient accountClient;

    @Autowired
    private TestRestTemplate rest;

    private Client client;

    @Before
    public void setup() {
        client = new Client(rest);
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

        doReturn(new Account(ACCOUNT_ID, 0.0))
                .when(accountClient)
                .getAccount(ACCOUNT_ID);

        ResponseEntity<Account> accountResponse = client.getUserAccount(user.getId());
        assertThat(accountResponse.getStatusCode()).isEqualTo(OK);
        assertThat(accountResponse.getBody()).isNotNull();

        Account account = accountResponse.getBody();
        assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(account.getBalance()).isEqualTo(0.0);

        verify(accountClient).getAccount(ACCOUNT_ID);

        // Add credit

        doReturn(new Account(ACCOUNT_ID, 0.0))
                .when(accountClient)
                .addCredit(eq(ACCOUNT_ID), anyDouble());

        accountResponse = client.addCreditToUserAccount(user.getId(), 123.0);
        assertThat(accountResponse.getStatusCode()).isEqualTo(OK);
        assertThat(accountResponse.getBody()).isNotNull();

        verify(accountClient).addCredit(eq(ACCOUNT_ID), anyDouble());

        // Add more credit

        accountResponse = client.addCreditToUserAccount(user.getId(), 543.0);
        assertThat(accountResponse.getStatusCode()).isEqualTo(OK);
        assertThat(accountResponse.getBody()).isNotNull();

        verify(accountClient, times(2)).addCredit(eq(ACCOUNT_ID), anyDouble());
    }
}