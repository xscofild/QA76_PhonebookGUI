package com.phonebook.tests.lesson15;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests15 extends TestBase15 {

    @Test
    public void newUserRegisterPositiveTest() {

        // click on Login link
        driver.findElement(By.cssSelector("[href='/login']")).click();

        // enter Email field

        // enter password field

        // click on Registration button

        // verify that Logout button present
    }
}