package homeworks.lesson22.tests;

import homeworks.lesson22.core.HW22ApplicationManager;
import homeworks.lesson22.core.HW22TestBase;
import homeworks.lesson22.data.HW22UserData;
import homeworks.lesson22.models.HW22User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/*
 HW22LoginTests — тесты авторизации пользователя.

 Позитивный: email + password из HW22Jenkins.xml (через @Parameters).
 Негативный: вход с пустым email → ожидаем alert.

 @Parameters — значения приходят из XML suite файла:
   <parameter name="email" value="..."/>
   <parameter name="password" value="..."/>
 Это позволяет менять данные без перекомпиляции кода.
*/
public class HW22LoginTests extends HW22TestBase {

    // Перед каждым тестом: если уже залогинен — выходим.
    // Гарантирует чистое состояние независимо от предыдущего теста.
    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    // email и password приходят из <parameter> в HW22Jenkins.xml.
    // Такой подход позволяет запускать один тест с разными данными из XML.
    @Parameters({"email", "password"})
    @Test
    public void loginPositiveTest(String email, String password) {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new HW22User()
                .setEmail(email)
                .setPassword(password));
        app.getUser().clickOnLoginButton();

        // После успешного входа кнопка Sign Out должна появиться в навигации.
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeWithoutEmailTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new HW22User()
                .setEmail("")                   // пустой email — невалидный сценарий
                .setPassword(HW22UserData.password));
        app.getUser().clickOnLoginButton();

        // Сервер должен вернуть ошибку — проявляется как browser alert.
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    // Утилитный тест — проверяет работу генератора рандомных email.
    // Полезен для отладки, не является частью бизнес-логики.
    @Test
    public void randomEmailGeneratorTest() {
        for (int i = 0; i < 6; i++) {
            System.out.println(HW22ApplicationManager.getRandomEmail());
        }
        // Просто генерируем — тест проходит если нет исключений.
        Assert.assertTrue(HW22ApplicationManager.getRandomEmail().endsWith("@test.com"));
    }
}
