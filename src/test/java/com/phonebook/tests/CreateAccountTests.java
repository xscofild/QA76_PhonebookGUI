package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 CreateAccountTests — тесты регистрации пользователя.

 Позитивный тест отключён (enabled=false) — аккаунт уже существует,
 повторная регистрация с теми же данными невозможна.
*/
public class CreateAccountTests extends TestBase {

    // Гарантируем чистое состояние: если залогинен — выходим.
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    // Отключён: для запуска нужен уникальный email (не существующий в системе).
    @Test(enabled = false)
    public void newUserRegisterPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void existedUserRegisterNegativeTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnRegistrationButton();

        // Система должна показать alert при попытке зарегистрировать существующий email.
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}