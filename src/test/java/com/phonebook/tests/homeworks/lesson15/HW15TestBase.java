package com.phonebook.tests.homeworks.lesson15;

/*
Homework – Automation (Lesson 15)

Requirements:
- Create a new TestBase class.
- Declare WebDriver inside TestBase.
- Implement @BeforeMethod and @AfterMethod.
- Implement reusable method: isElementPresent(By locator).
- Create CreateAccountTests class extending TestBase.
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class HW15TestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://telranedu.web.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }




}
