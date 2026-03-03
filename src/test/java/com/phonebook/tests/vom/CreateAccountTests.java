package com.phonebook.tests.vom;

import com.phonebook.tests.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 CreateAccountTests — тесты регистрации пользователя.

 Проверяют:
 - успешную регистрацию нового пользователя
 - ошибку при регистрации уже существующего пользователя
*/

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {

        // Если пользователь уже авторизован, то выходим из аккаунта
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(enabled = false)
    public void newUserRegisterPositiveTest() {

        // Регистрация пользователя
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnRegistrationButton();

        // После успешной регистрации должна появиться кнопка Sign Out
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void existedUserRegisterNegativeTest() {

        // Попытка зарегистрировать уже существующего пользователя
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnRegistrationButton();

        // Ожидаем появление alert
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}

