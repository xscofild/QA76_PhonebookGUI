package com.phonebook.tests;

import com.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 HomePageTests — проверки главной страницы.
*/
public class HomePageTests extends TestBase {

    // Гарантируем что мы на главной странице перед тестом.
    @BeforeMethod
    public void ensureHomePageIsOpen() {
        if (!app.getHomePage().isHomeComponentPresent()) {
            app.getHomePage().clickOnHomeLink();
        }
    }

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
    }
}