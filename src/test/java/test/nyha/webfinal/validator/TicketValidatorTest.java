package test.nyha.webfinal.validator;

import com.nyha.webfinal.validator.TicketValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TicketValidatorTest {
    @DataProvider(name = "validSeat")
    public static Object[][] validSeat() {
        return new Object[][]{{"1"}, {"124"}, {"12"}, {"1234"}};
    }

    @Test(dataProvider = "validSeat")
    public void isValidSeatPositiveTest(String seat) {
        Assert.assertTrue(TicketValidator.isValidSeat(seat));
    }

    @DataProvider(name = "invalidSeat")
    public static Object[][] invalidSeat() {
        return new Object[][]{{"Aa"}, {"333.3"}, {null}, {"5h"}, {"1234123412412341234134123412341234123412340"}};
    }

    @Test(dataProvider = "invalidSeat")
    public void isValidSeatNegativeTest(String seat) {
        Assert.assertFalse(TicketValidator.isValidSeat(seat));
    }
}
