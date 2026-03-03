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
 BaseHelper
 Базовый слой взаимодействия с UI.

 Предоставляет общие методы:
 - click
 - type
 - проверки элементов
 - работа с alert

 Не управляет браузером.
 WebDriver передаётся через ApplicationManager.
*/

public class BaseHelper {

    // Общий драйвер для всех UI-действий
    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Проверка существования элемента без падения теста
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // Безопасный ввод: клик → очистка → ввод
    // Если text == null, ничего не делаем (защита от случайных null)
    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }

    // Базовый клик
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Проверка появления alert с ожиданием
    // Не выбрасывает исключение, если alert не появился
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

    // Жёсткая пауза (анти-паттерн, использовать только если нет альтернативы)
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}