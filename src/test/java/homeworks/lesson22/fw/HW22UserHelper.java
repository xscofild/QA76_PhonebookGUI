package homeworks.lesson22.fw;

import homeworks.lesson22.core.HW22BaseHelper;
import homeworks.lesson22.models.HW22User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 HW22UserHelper — все действия пользователя в браузере: логин, регистрация, выход.

 Принцип: тесты не знают ни одного локатора (By, xpath, css).
 Всё взаимодействие с DOM — только здесь.

 Одна форма используется и для логина, и для регистрации.
 Разница — какую кнопку нажать: Login или Registration.
*/
public class HW22UserHelper extends HW22BaseHelper {

    public HW22UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    // Заполняет форму email + password.
    // Используется как перед clickOnLoginButton(), так и перед clickOnRegistrationButton().
    public void fillLoginRegisterForm(HW22User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }

    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    public void clickOnRegistrationButton() {
        click(By.name("registration"));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[.='Sign Out']"));
    }

    // true → пользователь авторизован (кнопка Sign Out видна).
    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    // true → пользователь НЕ авторизован (ссылка Login видна в навигации).
    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    // Сообщение об ошибке под формой, например: "Login failed with code 401".
    // Появляется при неверном пароле или попытке зарегистрировать существующий email.
    public boolean isErrorMessagePresent() {
        return isElementPresent(By.cssSelector(".login_login__3EHKB > div"));
    }
}
