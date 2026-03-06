package homeworks.lesson22.tests;

import homeworks.lesson22.core.HW22TestBase;
import homeworks.lesson22.data.HW22UserData;
import homeworks.lesson22.models.HW22User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 HW22CreateAccountTests — тесты регистрации нового пользователя.

 Позитивный тест: enabled=false — отключён, потому что аккаунт уже существует.
   Чтобы запустить — нужно вручную подставить уникальный email.

 Негативный тест: попытка зарегистрировать уже существующий email.
   Ожидаем: система показывает alert с ошибкой.
*/
public class HW22CreateAccountTests extends HW22TestBase {

    // Перед тестом: если залогинен — выходим, чтобы форма регистрации была доступна.
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    // enabled = false — тест пропускается при запуске suite.
    // Причина: HW22UserData.email уже зарегистрирован.
    // Для запуска: замени HW22UserData.email на новый уникальный адрес.
    @Test(enabled = false)
    public void newUserRegisterPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new HW22User()
                .setEmail(HW22UserData.email)
                .setPassword(HW22UserData.password));
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    // Негативный: регистрация с уже существующим email → alert от сервера (код 409).
    // isAlertPresent() принимает alert и возвращает true.
    @Test
    public void existedUserRegisterNegativeTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new HW22User()
                .setEmail(HW22UserData.email)
                .setPassword(HW22UserData.password));
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}
