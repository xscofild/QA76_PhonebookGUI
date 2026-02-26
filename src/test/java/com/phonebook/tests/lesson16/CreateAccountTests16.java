package com.phonebook.tests.lesson16;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests16 extends TestBase16 {
// Home work 17 i 25 stroka

    @Test
    public void newUserRegisterPositiveTest() {
        // int i = (int) ((System.currentTimeMillis() / 1000) % 3600); // Получаем число от 0 до 3600, меняющееся каждую секунду.
        // driver.findElement(By.name("email")).sendKeys("serdarkerimov" + i + "@gmail.com");

        clickOnLoginLink();
        fillLoginRegisterForm("serdarkerimov@gmail.com", "Qwertz123!");
        clickOnRegistrationButton();
        Assert.assertTrue(isSignOutButtonPresent());
    }


    @Test
    public void existedUserRegisterNegativeTest() {

        clickOnLoginLink();
        fillLoginRegisterForm("serdarkerimov@gmail.com", "Qwertz123!");
        clickOnRegistrationButton();
        Assert.assertTrue(isAlertPresent());
    }

}