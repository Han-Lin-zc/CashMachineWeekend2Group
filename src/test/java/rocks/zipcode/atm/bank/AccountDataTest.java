package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDataTest {

    @Test
    public void getId() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500);
        int expected = 1000;
        int actual = accData.getId();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getName() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500);
        String expected = "Basic";
        String actual = accData.getName();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getEmail() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500);
        String expected = "basic@gmail.com";
        String actual = accData.getEmail();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getBalance() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500);
        float expected = 500;
        float actual = accData.getBalance();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testToString() {
        AccountData accData = new AccountData(1000, "Basic", "basic@gmail.com", 500);
        String name = accData.getName();
        float balance = accData.getBalance();
        String expected = name + ", Havak Inc. Welcomes You! :D" + '\n' + '\n' + '\n' + "Balance: " + balance;
        Assert.assertEquals(expected, accData.toString());
    }
}