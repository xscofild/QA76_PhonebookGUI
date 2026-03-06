package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 UserHelper — все действия пользователя в браузере: логин, регистрация, выход.

 Принцип: тесты не знают ни одного локатора (By, xpath, css).
 Всё взаимодействие с DOM — только здесь.

 Одна форма используется и для логина, и для регистрации.
 Разница — какую кнопку нажать: Login или Registration.
*/
public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    // Заполняет форму email + password.
    // Используется как перед loginButton, так и перед registrationButton.
    public void fillLoginRegisterForm(User user) {
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
