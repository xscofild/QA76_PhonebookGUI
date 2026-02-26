package com.phonebook.tests.homeworks.lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

/*
Homework – Lesson 16
Project: DemoWebShop

Implement two registration tests using TestBase methods.

1. Positive registration test:
   - Click "Register" in header.
   - Fill registration form with dynamic email.
   - Click "Register".
   - Verify successful registration via "Log out" link.
   - Click "Log out" and verify it disappears.

2. Negative registration test:
   - Click "Register".
   - Fill form with existing email.
   - Click "Register".
   - Verify error message is displayed.
   - Verify error text equals: "The specified email already exists".
   - Verify "Log out" link is not present.
*/

public class HW16TestBase {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void clickOnRegisterLink() {
        click(By.cssSelector("[href='/register']"));
    }

    public void fillRegisterForm(String firstName, String lastName, String email, String password) {
        type(By.id("FirstName"), firstName);
        type(By.id("LastName"), lastName);
        type(By.id("Email"), email);
        type(By.id("Password"), password);
        type(By.id("ConfirmPassword"), password);
    }

    public void clickOnRegisterButton() {
        click(By.id("register-button"));
    }

    public boolean isLogoutLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/logout']"));
    }

    public boolean isAlertPresent() {
        return !driver.findElements(By.cssSelector("div.validation-summary-errors li")).isEmpty();
    }

    public String getAlertText() {
        return driver.findElement(By.cssSelector("div.validation-summary-errors li")).getText();
    }

    public String generateUniqueEmail() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        return "serdarkerimov" + i + "@gmail.com";
    }
}