package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDataTest {

    @Test
    public void getId() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500, "basic");
        int expected = 1000;
        int actual = accData.getId();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getName() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500, "basic");
        String expected = "Basic";
        String actual = accData.getName();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getEmail() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500, "basic");
        String expected = "basic@gmail.com";
        String actual = accData.getEmail();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getBalance() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500, "basic");
        float expected = 500.00f;
        float actual = accData.getBalance();
        Assert.assertEquals(expected,actual, 0.0);
    }

    @Test
    public void testToString() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500, "basic");
        String name = accData.getName();
        String accountType = accData.getTypeAccount();
        String balance = String.format("%1$.2f", accData.getBalance());
        String expected = name + ", Havak Inc. Welcomes you! \n\n" +
                "As a prime member of our " + accountType + " account,\n" +
                "we wish you have wonderful day!" + '\n' + '\n' + "Balance: " + balance;
        Assert.assertEquals(expected, accData.toString());
    }
}