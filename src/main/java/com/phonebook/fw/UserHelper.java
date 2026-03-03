package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 UserHelper
 Действия пользователя:
 login, registration, проверки авторизации.
*/

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    // Проверка авторизации (кнопка Sign Out отображается)
    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    // Переход на страницу логина
    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    // Заполнение формы login/registration
    public void fillLoginRegisterForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }

    // Нажатие Login
    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    // Нажатие Registration
    public void clickOnRegistrationButton() {
        click(By.name("registration"));
    }

    // Проверка наличия ссылки Login (пользователь не авторизован)
    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    // Выход из системы
    public void clickOnSignOutButton() {
        click(By.xpath("//button[.='Sign Out']"));
    }
}