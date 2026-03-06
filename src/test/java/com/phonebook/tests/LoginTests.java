package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/*
 LoginTests — тесты авторизации пользователя.

 Позитивный: email + password из regression.xml (через @Parameters).
 Негативный: вход с пустым email → ожидаем alert.

 @Parameters — значения приходят из XML suite файла:
   <parameter name="email" value="..."/>
   <parameter name="password" value="..."/>
 Это позволяет менять данные без перекомпиляции кода.
*/
public class LoginTests extends TestBase {

    // Перед каждым тестом: если уже залогинен — выходим.
    // Гарантирует чистое состояние независимо от предыдущего теста.
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    // email и password приходят из <parameter> в regression.xml.
    // Такой подход позволяет запускать один тест с разными данными из XML.
    @Parameters({"email", "password"})
    @Test
    public void loginPositiveTest(String email, String password) {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(email)
                .setPassword(password));
        app.getUser().clickOnLoginButton();

        // После успешного входа кнопка Sign Out должна появиться в навигации.
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeWithoutEmailTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("")          // пустой email — невалидный сценарий
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();

        // Сервер должен вернуть ошибку — проявляется как browser alert.
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}
