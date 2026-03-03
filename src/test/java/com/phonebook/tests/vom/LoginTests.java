package com.phonebook.tests.vom;

import com.phonebook.tests.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 LoginTests — тесты авторизации пользователя.

 Проверяют:
 - успешный вход с валидными данными
 - ошибку при отсутствии email
*/

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {

        // Если пользователь уже авторизован, то выходим из аккаунта
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    public void loginPositiveTest() {

        // Авторизация с корректными данными
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();

        // После успешного входа должна появиться кнопка Sign Out
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeWithoutEmailTest() {

        // Попытка входа без указания email
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("")
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();

        // Ожидаем сообщение об ошибке (alert)
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}

