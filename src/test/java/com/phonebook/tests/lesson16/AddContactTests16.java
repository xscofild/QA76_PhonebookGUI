package com.phonebook.tests.lesson16;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests16 extends TestBase16 {
    @BeforeMethod
    public void precondition() {
        clickOnLoginLink();
        fillLoginRegisterForm("kristitomash33@gmail.com", "Aa12345!");
        click(By.name("login"));
    }

    @Test
    public void addContactPositiveTest() {

        click(By.cssSelector("[href='/add']"));
        type(By.xpath("//input[1]"), "John");
        type(By.xpath("//input[2]"), "Wick");
        type(By.xpath("//input[3]"), "1996664444");
        type(By.xpath("//input[4]"), "johnwick@xxx.com");
        type(By.xpath("//input[5]"), "New York");
        type(By.xpath("//input[6]"), "Killer");
        click(By.cssSelector(".add_form__2rsm2 button"));
        Assert.assertTrue(isContactCreatedByText("John"));


    }

    @AfterMethod
    public void postCondition() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));

    }
}


























