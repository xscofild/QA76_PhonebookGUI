package homeworks.lesson19.fw;

import homeworks.lesson19.core.HW19BaseHelper;
import homeworks.lesson19.models.HW19User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HW19UserHelper extends HW19BaseHelper {

    public HW19UserHelper(WebDriver driver) {
        super(driver);
    }

    // Локаторы
    private final By registerLink   = By.cssSelector("a.ico-register");
    private final By loginLink      = By.cssSelector("a.ico-login");
    private final By logoutLink     = By.cssSelector("a.ico-logout");
    private final By genderMale     = By.id("gender-male");
    private final By firstName      = By.id("FirstName");
    private final By lastName       = By.id("LastName");
    private final By email          = By.id("Email");
    private final By password       = By.id("Password");
    private final By confirmPass    = By.id("ConfirmPassword");
    private final By registerButton = By.id("register-button");
    private final By loginButton    = By.cssSelector("input.login-button");

    // Регистрация нового пользователя
    public void register(HW19User user) {
        click(registerLink);
        click(genderMale);
        type(firstName, user.getFirstName());
        type(lastName, user.getLastName());
        type(email, user.getEmail());
        type(password, user.getPassword());
        type(confirmPass, user.getPassword());
        click(registerButton);
    }

    // Логин существующего пользователя
    public void login(String mail, String pass) {
        click(loginLink);
        type(email, mail);
        type(password, pass);
        click(loginButton);
    }

    // Проверка: пользователь авторизован (есть кнопка logout)
    public boolean isLoggedIn() {
        return isElementPresent(logoutLink);
    }

    // Выход из системы
    public void logout() {
        if (isElementPresent(logoutLink)) {
            click(logoutLink);
        }
    }
}