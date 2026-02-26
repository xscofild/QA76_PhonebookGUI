package com.phonebook.tests.homeworks.lesson16;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

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

public class HW16CreateAccountTests extends HW16TestBase {

    @Test
    public void newUserRegisterPositiveTest() {
        clickOnRegisterLink();
        String email = generateUniqueEmail();
        fillRegisterForm("Serdar", "Kerimov", email, "Qwertz123!");
        clickOnRegisterButton();
        Assert.assertTrue(isLogoutLinkPresent());
        click(By.cssSelector("[href='/logout']"));
        Assert.assertFalse(isLogoutLinkPresent());
    }

    @Test
    public void existedUserRegisterNegativeTest() {
        clickOnRegisterLink();
        fillRegisterForm("Serdar", "Kerimov", "serdarkerimovv@gmail.com", "Qwertz123!");
        clickOnRegisterButton();
        Assert.assertTrue(isAlertPresent());
        Assert.assertEquals(getAlertText(), "The specified email already exists");
        Assert.assertFalse(isLogoutLinkPresent());
    }
}