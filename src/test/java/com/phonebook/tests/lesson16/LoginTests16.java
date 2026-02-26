package com.phonebook.tests.lesson16;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests16 extends TestBase16 {


    @Test
    public void loginPositiveTest() {

        clickOnLoginLink();
        fillLoginRegisterForm("kristitomash33@gmail.com", "Aa12345!");
        clickOnLoginButton();
        Assert.assertTrue(isSignOutButtonPresent());
    }


}
