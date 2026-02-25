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

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTestsHW extends TestBaseHW {

    @Test
    public void newUserRegisterPositiveTest() {

        // click on Login link
        driver.findElement(By.cssSelector("[href='/login']")).click();

        // enter email
        driver.findElement(By.name("email")).sendKeys("test" + System.currentTimeMillis() + "@mail.com");

        // enter password
        driver.findElement(By.name("password")).sendKeys("Password123!");

        // click on Registration button
        driver.findElement(By.xpath("//button[normalize-space()='Registration']")).click();

        // verify that user is logged in (Sign Out button is visible)
        Assert.assertTrue(isElementPresent(By.xpath("//button[normalize-space()='Sign Out']"))
        );
    }
}