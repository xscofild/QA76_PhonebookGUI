package com.phonebook.tests.lesson17._02_helpers;

import com.phonebook.tests.lesson17._03_model._3_1_User17;
import org.openqa.selenium.By;

/*
 UserHelper17 — отвечает за действия пользователя:
 login, registration и проверки авторизации.
*/

public class _2_2_UserHelper17 extends _2_1_BaseHelper17 {

    // Проверяет, авторизован ли пользователь (кнопка Sign Out видна).
    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    // Нажатие кнопки регистрации на странице логина.
    public void clickOnRegistrationButton() {
        click(By.name("registration"));
    }

    // Заполняет форму login/registration данными из объекта User17 (VOM).
    public void fillLoginRegisterForm(_3_1_User17 user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }

    // Переход на страницу логина.
    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    // Нажатие кнопки Login.
    public void clickOnLoginButton() {
        click(By.name("login"));
    }
}