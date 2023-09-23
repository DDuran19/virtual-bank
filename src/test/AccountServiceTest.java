package test;

import main.com.virtualbank.exceptions.CredentialsException;
import main.com.virtualbank.exceptions.WithdrawException;
import main.com.virtualbank.service.AccountService;
import main.com.virtualbank.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    private AccountService BANK_SERVICE;

    @Before
    public void setUp() {
        BANK_SERVICE = new AccountServiceImpl();

        BANK_SERVICE.addAccountHolder("user1", "password1", "First", "Name");
        BANK_SERVICE.addAccountHolder("user2", "password2", "Second", "Name");

        BANK_SERVICE.deposit("user1", "password1", 100);
        BANK_SERVICE.deposit("user2", "password2", 100);
    }

    @Test(expected = CredentialsException.class)
    public void testWrongCredentials() {
        BANK_SERVICE.deposit("user1", "WrongPassword", 200);
        BANK_SERVICE.withdraw("noSuchUser", "password2", 200);

    }


    @Test
    public void testDeposit() {
        BANK_SERVICE.deposit("user1", "password1", 200);
        BANK_SERVICE.deposit("user2", "password2", 200);

        assertEquals(300, BANK_SERVICE.getAccountBalance("user1"), 0.01);
        assertEquals(300, BANK_SERVICE.getAccountBalance("user1"), 0.01);
    }

    @Test
    public void testWithdraw() {
        BANK_SERVICE.withdraw("user1", "password1", 50);
        BANK_SERVICE.withdraw("user2", "password2", 50);

        assertEquals(50, BANK_SERVICE.getAccountBalance("user1"), 0.01);
        assertEquals(50, BANK_SERVICE.getAccountBalance("user2"), 0.01);

    }

    @Test(expected = WithdrawException.class)
    public void testInsufficientFundsWithdraw() {
        BANK_SERVICE.withdraw("user1", "password1", 5000);
    }

    @Test
    public void testTransfer() {
        BANK_SERVICE.transfer("user1", "password1", "user2", 25);

        assertEquals(75, BANK_SERVICE.getAccountBalance("user1"), 0.01);
        assertEquals(125, BANK_SERVICE.getAccountBalance("user2"), 0.01);
    }

    @Test(expected = WithdrawException.class)
    public void testInsufficientFundsTransfer() {
        BANK_SERVICE.withdraw("user1", "password1", 5000);
    }
}
