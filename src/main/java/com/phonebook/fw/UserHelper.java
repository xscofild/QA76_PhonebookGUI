package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 UserHelper — действия пользователя: логин, регистрация, логаут.

 Локаторы инкапсулированы здесь.
 Тесты ничего не знают о By, xpath и css.
*/
public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    // Одна форма используется и для логина, и для регистрации.
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

    // true — пользователь вошёл в систему
    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    // true — пользователь не авторизован (гостевое состояние)
    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    // Сообщение об ошибке при регистрации, например: "Registration failed with code 409"
    public boolean isErrorMessagePresent() {
        return isElementPresent(By.cssSelector(".login_login__3EHKB > div"));
    }
}