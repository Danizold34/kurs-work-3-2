package test.nyha.webfinal.validator;

import com.nyha.webfinal.validator.BankValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BankValidatorTest {
    @DataProvider(name = "validAccountNumber")
    public static Object[][] validAccountNumber() {
        return new Object[][]{{"1"}, {"12341253"}, {"345345"}, {"12342"}};
    }

    @Test(dataProvider = "validAccountNumber")
    public void isValidAccountNumberPositiveTest(String accountNumber) {
        Assert.assertTrue(BankValidator.isValidAccountNumber(accountNumber));
    }

    @DataProvider(name = "invalidAccountNumber")
    public static Object[][] invalidAccountNumber() {
        return new Object[][]{{"Aa"}, {"333.3"}, {null}, {"5h"}, {"1234123412412341234134123412341234123412340"}};
    }

    @Test(dataProvider = "invalidAccountNumber")
    public void isValidAccountNumberNegativeTest(String accountNumber) {
        Assert.assertFalse(BankValidator.isValidAccountNumber(accountNumber));
    }
}
