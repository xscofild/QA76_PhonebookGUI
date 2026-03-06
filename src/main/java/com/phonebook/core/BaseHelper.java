package com.phonebook.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

// Базовый слой для всех helper-ов.
// Универсальные методы: поиск, клик, ввод, alert, скриншот.
// Конкретные страницы здесь не знает — только работа с UI.
public class BaseHelper {

    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    // true если хотя бы один элемент найден (не бросает исключение).
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // Клик для фокуса → clear → sendKeys.
    // text == null → поле пропускается (для частичного заполнения форм).
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

    // Ждёт alert до 5 сек, принимает и возвращает true.
    // Если не появился — возвращает false (не бросает исключение).
    public boolean isAlertPresent() {
        try {
            Alert alert = getWait(5).until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // Антипаттерн — только если нет явного DOM-условия для ожидания.
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Скриншот при падении теста. Закрывает alert если открыт.
    // mkdirs() — создаёт папку screenshots/ если не существует (Jenkins).
    public String takeScreenshot() {
        try {
            Alert alert = getWait(3).until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception ignored) {
        }

        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/screen-" + System.currentTimeMillis() + ".png");
        screenshot.getParentFile().mkdirs();

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return screenshot.getAbsolutePath();
    }
}
