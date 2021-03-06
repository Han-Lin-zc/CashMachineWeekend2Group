package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getAccountData() {
        AccountData expected = new AccountData(1000, "Basic", "basic@gmail.com", 500, "basic");
        Account acc = new BasicAccount(expected);
        Assert.assertEquals(expected, acc.getAccountData());

    }

    @Test
    public void getBalance() {
        AccountData test = new AccountData(1000, "Basic", "basic@gmail.com", 500, "basic");
        float expected = 500.00f;
        Account acc = new BasicAccount(test);
        Assert.assertEquals(expected,acc.getBalance(), 0.0);
    }
}