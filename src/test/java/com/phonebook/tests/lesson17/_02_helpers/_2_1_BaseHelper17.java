package com.phonebook.tests.lesson17._02_helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
 BaseHelper17 — общий класс для всех helper-ов.

 Содержит базовые действия с UI:
 click, type, проверки элементов и alert.

 Не запускает браузер.
 Использует WebDriver, который должен быть передан извне
 (через ApplicationManager).
*/

public class _2_1_BaseHelper17 {

    // WebDriver используется для всех операций с браузером.
    WebDriver driver;

    // Проверяет, существует ли элемент на странице.
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    // Ввод текста в поле: клик → очистка → ввод.
    // null запрещён, чтобы не было скрытых ошибок в тестах.
    public void type(By locator, String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text is null. Use empty string \"\" for negative scenarios.");
        }
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    // Клик по элементу.
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Проверяет появление alert.
    // Ждёт до 20 секунд.
    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.alertIsPresent());
        return alert != null;
    }

    // Жёсткая пауза (использовать аккуратно).
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}