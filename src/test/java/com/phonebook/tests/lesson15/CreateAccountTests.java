package com.phonebook.tests.lesson15;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test
    public void newUserRegisterPositiveTest() {

        // click on Login link
        driver.findElement(By.cssSelector("[href='/login']")).click();

        // enter Email field
        driver.findElement(By.name("email")).sendKeys("test" + System.currentTimeMillis() + "@mail.com");

        // enter password field
        driver.findElement(By.name("password")).sendKeys("Password123!");

        // click on Registration button
        driver.findElement(By.cssSelector("[type='submit']")).click();

        // verify that Logout button present
        Assert.assertTrue(!driver.findElements(By.xpath("//button[text()='Logout']")).isEmpty());
    }
}