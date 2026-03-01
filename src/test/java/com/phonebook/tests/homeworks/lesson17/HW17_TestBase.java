package com.phonebook.tests.homeworks.lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/*
Homework – Lesson 17
Project: DemoWebShop

Implement add-to-cart test using TestBase methods.

Test scenario:
1. Login with valid credentials.
2. Add "14.1-inch Laptop" to cart.
3. Verify success popup text:
   "The product has been added to your shopping cart".
4. Open Shopping cart.
5. Verify product is present in cart.
6. Logout.

Structure:
- HW17_TestBase (setup, teardown, reusable methods)
- HW17UserData (credentials)
- HW17_AddToCartFeaturedProduct2Test (test only)
*/

public class HW17_TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected int stepCounter;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://demowebshop.tricentis.com/");

        stepCounter = 1;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }


    protected void step(String msg) {
        System.out.println("[STEP-" + stepCounter++ + "] " + msg);
    }

    public void loginWithValidData(String email, String password) {
        driver.findElement(By.cssSelector("a.ico-login[href='/login']")).click();
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input.button-1.login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.ico-logout")
        ));
    }


    public void loginSuccess(String cssSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    public void add2ndProductToShoppingCart() {
        By laptopAddToCart = By.xpath("//a[@href='/141-inch-laptop']/ancestor::div[contains(@class,'item-box')]//input[@value='Add to cart']");
        wait.until(ExpectedConditions.elementToBeClickable(laptopAddToCart)).click();
    }

    public void shoppingCartOpenning() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#bar-notification a[href='/cart']")
        )).click();
    }

    public void logout() {
        driver.findElement(By.cssSelector("a.ico-logout[href='/logout']")).click();
    }

}