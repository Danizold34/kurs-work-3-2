package test.nyha.webfinal.validator;

import com.nyha.webfinal.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    @DataProvider(name = "validUsername")
    public static Object[][] validUsername() {
        return new Object[][]{{"Dovlet"}, {"Nastya"}, {"Sasha"}, {"lol"}};
    }

    @Test(dataProvider = "validUsername")
    public void isValidUsernamePositiveTest(String username) {
        Assert.assertTrue(UserValidator.isValidUsername(username));
    }

    @DataProvider(name = "invalidUsername")
    public static Object[][] invalidUsername() {
        return new Object[][]{{"Aa"}, {"333.3"}, {null}, {"5h"}, {"0"}};
    }

    @Test(dataProvider = "invalidUsername")
    public void isValidUsernameNegativeTest(String username) {
        Assert.assertFalse(UserValidator.isValidUsername(username));
    }

    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return new Object[][]{{"gret.andrey@gmail.com"}, {"lol@tut.by"}, {"ola@gmail.com"}, {"sasha@gmail.com"}};
    }

    @Test(dataProvider = "validEmail")
    public void isValidEmailPositiveTest(String email) {
        Assert.assertTrue(UserValidator.isValidEmail(email));
    }

    @DataProvider(name = "invalidEmail")
    public static Object[][] invalidEmail() {
        return new Object[][]{{"gret.andrey@gmailcom"}, {"333.3"}, {null}, {"gret.andreygmail.com"}, {"0@gmail."}};
    }

    @Test(dataProvider = "invalidEmail")
    public void isValidEmailNegativeTest(String email) {
        Assert.assertFalse(UserValidator.isValidUsername(email));
    }

    @DataProvider(name = "validPassword")
    public static Object[][] validPAssword() {
        return new Object[][]{{"nDpgywj3d2"}, {"jdfjnf7Dewfwef"}, {"1h1hsif4Fe"}, {"484JfksFHDE"}};
    }

    @Test(dataProvider = "validPassword")
    public void isValidPasswordPositiveTest(String password) {
        Assert.assertTrue(UserValidator.isValidPassword(password));
    }

    @DataProvider(name = "invalidPassword")
    public static Object[][] invalidPassword() {
        return new Object[][]{{"DjdkfjdFJE"}, {"Fhewj4f"}, {null}, {"Fjer  efhw"}, {"8vkekej7rfn"}};
    }

    @Test(dataProvider = "invalidPassword")
    public void isValidPasswordNegativeTest(String password) {
        Assert.assertFalse(UserValidator.isValidPassword(password));
    }
}