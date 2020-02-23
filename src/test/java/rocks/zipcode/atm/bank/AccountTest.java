package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getAccountData() {
        AccountData expected = new AccountData(1000, "Basic", "basic@gmail.com", 500);
        Account acc = new BasicAccount(expected);
        Assert.assertEquals(expected, acc.getAccountData());

    }

    @Test
    public void deposit() {
    }

    @Test
    public void withdraw() {
    }

    @Test
    public void canWithdraw() {
    }

    @Test
    public void getBalance() {
        AccountData expected = new AccountData(1000, "Basic", "basic@gmail.com", 500);
        Account acc = new BasicAccount(expected);
        Assert.assertEquals(expected,acc.getBalance());
    }
}