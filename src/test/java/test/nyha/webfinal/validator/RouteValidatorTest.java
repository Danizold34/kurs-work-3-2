package test.nyha.webfinal.validator;

import com.nyha.webfinal.validator.RouteValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RouteValidatorTest {
    @DataProvider(name = "validStation")
    public static Object[][] validStation() {
        return new Object[][]{{"Minsk"}, {"Zhabinka1"}, {"lol"}, {"Baranovichi"}};
    }

    @Test(dataProvider = "validStation")
    public void isValidStationPositiveTest(String station) {
        Assert.assertTrue(RouteValidator.isValidStation(station));
    }

    @DataProvider(name = "invalidStation")
    public static Object[][] invalidStation() {
        return new Object[][]{{"Aa"}, {"333.3"}, {null}, {"5h"}, {"1234123412412341234134123412341234123412340"}};
    }

    @Test(dataProvider = "invalidStation")
    public void isValidStationNegativeTest(String station) {
        Assert.assertFalse(RouteValidator.isValidStation(station));
    }
}
