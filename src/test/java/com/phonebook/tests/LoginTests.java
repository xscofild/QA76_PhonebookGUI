package com.phonebook.tests;

import com.phonebook.core.ApplicationManager;
import com.phonebook.data.UserData;
import com.phonebook.models.User;
import com.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    // email и password приходят из XML.
    // Если запускать тест прямо из IDEA — возьмутся значения по умолчанию.
    @Parameters({"email", "password"})
    @Test(priority = 1)
    public void loginPositiveTest(
            @Optional("serdarkerimov@gmail.com") String email,
            @Optional("Qwertz123!") String password) {

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
                .setPassword(UserData.password));

        app.getUser().clickOnLoginButton();

        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    // Проверка генератора случайных email.
    @Test
    public void randomEmailTest() {
        for (int i = 0; i < 6; i++) {
            System.out.println(ApplicationManager.getRandomEmail());
        }

        Assert.assertTrue(ApplicationManager.getRandomEmail().endsWith("@test.com"));
    }
}