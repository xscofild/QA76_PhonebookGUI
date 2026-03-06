package com.phonebook.tests;

import com.phonebook.tests.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getHomePage().isHomeComponentPresent()) {
            app.getHomePage().clickOnHomeLink();
        }
    }

    @Test(groups = "smoky")
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
    }
}
