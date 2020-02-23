package rocks.zipcode.atm;

import javafx.scene.Parent;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashMachineAppTest {

    @Test
    public void beforeContentErrorMessageTest() {
        CashMachineApp cm = new CashMachineApp();
        String expected = "Please enter your ID";
        Parent actual = cm.beforeContent();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void accntContent() {

    }

    @Test
    public void numberOrNot() {
    }

    @Test
    public void start() {
    }
}