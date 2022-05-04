package test.nyha.webfinal.validator;

import com.nyha.webfinal.validator.PassengerValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PassengerValidatorTest {
    @DataProvider(name = "validName")
    public static Object[][] validName() {
        return new Object[][]{{"Andry"}, {"PASHA"}, {"lol"}, {"Liza"}};
    }

    @Test(dataProvider = "validName")
    public void isValidNamePositiveTest(String name) {
        Assert.assertTrue(PassengerValidator.isValidName(name));
    }

    @DataProvider(name = "invalidName")
    public static Object[][] invalidName() {
        return new Object[][]{{"Aa"}, {"333.3"}, {null}, {"5h"}, {"1234123412412341234134123412341234123412340"}};
    }

    @Test(dataProvider = "invalidName")
    public void isValidNameNegativeTest(String name) {
        Assert.assertFalse(PassengerValidator.isValidName(name));
    }

    @DataProvider(name = "validNumber")
    public static Object[][] validNumber() {
        return new Object[][]{{"745843453AB"}, {"3752923453"}, {"123445"}, {"34522"}};
    }

    @Test(dataProvider = "validNumber")
    public void isValidNumberPositiveTest(String number) {
        Assert.assertTrue(PassengerValidator.isValidNumber(number));
    }

    @DataProvider(name = "invalidNumber")
    public static Object[][] invalidNumber() {
        return new Object[][]{{"Aa"}, {"333.3"}, {null}, {"5h"}, {"1234123412412341234134123412341234123412340"}};
    }

    @Test(dataProvider = "invalidNumber")
    public void isValidNumberNegativeTest(String number) {
        Assert.assertFalse(PassengerValidator.isValidName(number));
    }
}
