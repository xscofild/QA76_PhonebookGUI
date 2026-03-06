package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 CreateAccountTests — тесты регистрации нового пользователя.

 Позитивный тест: enabled=false — отключён, потому что аккаунт уже существует.
   Чтобы запустить — нужно вручную подставить уникальный email.

 Негативный тест: попытка зарегистрировать уже существующий email.
   Ожидаем: система показывает alert с ошибкой.

 Запускается через: gradlew negative (negative.xml)
*/
public class CreateAccountTests extends TestBase {

    // Перед тестом: если залогинен — выходим, чтобы форма регистрации была доступна.
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    // enabled = false — тест пропускается при запуске suite.
    // Причина: UserData.email уже зарегистрирован. Повторная регистрация вернёт ошибку 409.
    // Для запуска: замени UserData.email на новый уникальный адрес.
    @Test(enabled = false)
    public void newUserRegisterPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    // Негативный: регистрация с уже существующим email → alert от сервера.
    // isAlertPresent() принимает alert и возвращает true.
    @Test
    public void existedUserRegisterNegativeTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}
