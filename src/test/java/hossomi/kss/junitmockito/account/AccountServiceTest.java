package hossomi.kss.junitmockito.account;

import hossomi.kss.junitmockito.user.User;
import hossomi.kss.junitmockito.user.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private UserService userService;
    private AccountClient accountClient;
    private AccountService accountService;

    @Before
    public void setup() {
        userService = mock(UserService.class);
        accountClient = mock(AccountClient.class);
        accountService = new AccountService(userService, accountClient);
    }

    @Test
    public void testAddCreditToExistingAccount() {
        final String ACCOUNT_ID = "ACC01234";
        final long USER_ID = 1L;

        doReturn(Optional.of(User.builder()
                .id(USER_ID)
                .age(10)
                .name("Hossomi")
                .accountId(ACCOUNT_ID)
                .build()))
                .when(userService)
                .get(USER_ID);

        doReturn(Account.builder()
                .id(ACCOUNT_ID)
                .balance(0.0)
                .build())
                .when(accountClient)
                .addCredit(eq(ACCOUNT_ID), anyDouble());

        Optional<Account> account = accountService.addCreditToUserAccount(USER_ID, 10.0);

        verify(userService).get(USER_ID);
        verify(accountClient).addCredit(ACCOUNT_ID, 10.0);
        assertThat(account).hasValue(Account.builder()
                .id(ACCOUNT_ID)
                .balance(0.0)
                .build());
    }

    @Test
    public void testAddCreditToUnknownAccount() {
        final long USER_ID = 1L;

        doReturn(Optional.empty())
                .when(userService)
                .get(USER_ID);

        Optional<Account> account = accountService.addCreditToUserAccount(USER_ID, 10.0);

        verify(userService).get(USER_ID);
        verify(accountClient, never()).addCredit(any(), anyDouble());
        assertThat(account).isEmpty();
    }
}