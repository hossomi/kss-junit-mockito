package hossomi.kss.junitmockito.integration;

import hossomi.kss.junitmockito.account.Account;
import hossomi.kss.junitmockito.user.User;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

public class Client {

    private TestRestTemplate rest;

    public Client(int port) {
        this(new TestRestTemplate(new RestTemplateBuilder()
                .rootUri("http://localhost:" + port)));
    }

    public Client(TestRestTemplate rest) {
        this.rest = rest;
    }

    public ResponseEntity<User> createUser(User user) {
        return rest.postForEntity("/users", user, User.class);
    }

    public ResponseEntity<User> getUser(Long userId) {
        return rest.getForEntity("/users/{userId}", User.class, userId);
    }

    public ResponseEntity<Account> getUserAccount(Long userId) {
        return rest.getForEntity("/users/{userId}/account", Account.class, userId);
    }

    public ResponseEntity<Account> addCreditToUserAccount(Long userId, double value) {
        return rest.postForEntity("/users/{userId}/account", value, Account.class, userId);
    }
}
