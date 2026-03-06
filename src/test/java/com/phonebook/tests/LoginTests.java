package com.phonebook.tests;

import com.phonebook.core.ApplicationManager;
import com.phonebook.data.UserData;
import com.phonebook.models.User;
import com.phonebook.tests.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    // email и password передаются из XML-suite через @Parameters.
    @Parameters({"email", "password"})
    @Test(priority = 1)
    public void loginPositiveTest(String email, String password) {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(email)
                .setPassword(password));
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test(priority = 2)
    public void loginNegativeWithoutEmailTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setPassword(UserData.password)); // email = null → поле пустое
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    // Утилитный тест — проверяет генератор случайных email.
    @Test
    public void randomEmailTest() {
        for (int i = 0; i < 6; i++) {
            System.out.println(ApplicationManager.getRandomEmail());
        }
        Assert.assertTrue(ApplicationManager.getRandomEmail().endsWith("@test.com"));
    }
}
