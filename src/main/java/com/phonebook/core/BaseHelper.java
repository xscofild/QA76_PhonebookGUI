package com.phonebook.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
 BaseHelper — базовый слой UI-взаимодействий.

 Все helpers наследуют этот класс и получают
 готовые методы click/type/проверок.
 Логики конкретных страниц не содержит.
*/
public class BaseHelper {

    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // Клик перед вводом нужен для фокуса на элементе.
    // Пропускаем если text == null — защита от необязательных полей.
    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Принимает alert если он появился, возвращает false если нет.
    // Используется там, где alert появляется не всегда.
    public boolean isAlertPresent() {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Анти-паттерн. Использовать только если нет явного условия для ожидания.
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}