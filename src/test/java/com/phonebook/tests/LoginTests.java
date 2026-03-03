package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 LoginTests — тесты авторизации пользователя.

 Позитивный: успешный вход с валидными данными.
 Негативный: попытка входа без email.
*/
public class LoginTests extends TestBase {

    // Гарантируем чистое состояние: если залогинен — выходим.
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    public void loginPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeWithoutEmailTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("")   // пустой email — невалидный сценарий
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();

        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}