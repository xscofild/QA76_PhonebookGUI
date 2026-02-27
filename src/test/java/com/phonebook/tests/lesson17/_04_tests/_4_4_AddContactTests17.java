package com.phonebook.tests.lesson17._04_tests;

import com.phonebook.tests.lesson17._01_manager._1_2_TestBase17;
import com.phonebook.tests.lesson17._03_model._3_2_Contact17;
import com.phonebook.tests.lesson17._03_model._3_4_ContactData17;
import com.phonebook.tests.lesson17._03_model._3_1_User17;
import com.phonebook.tests.lesson17._03_model._3_3_UserData17;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 AddContactTests17 — позитивный сценарий добавления контакта.

 Проверяет, что контакт сохраняется и появляется в списке.
*/

public class _4_4_AddContactTests17 extends _1_2_TestBase17 {

    @BeforeMethod
    public void precondition() {
        // Авторизация перед тестом
        app17.clickOnLoginLink();
        app17.fillLoginRegisterForm(new _3_1_User17()
                .setEmail(_3_3_UserData17.email)
                .setPassword(_3_3_UserData17.password));
        app17.clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {

        // Добавление контакта с валидными данными
        app17.clickOnAddLink();
        app17.fillContactForm(new _3_2_Contact17()
                .setName(_3_4_ContactData17.name)
                .setSurname(_3_4_ContactData17.surname)
                .setPhoneNumber(_3_4_ContactData17.phoneNumber)
                .setEmail(_3_4_ContactData17.email)
                .setAddress(_3_4_ContactData17.address)
                .setDescription(_3_4_ContactData17.description));

        app17.clickOnSaveButton();

        // Проверяем появление контакта в списке (по имени)
        Assert.assertTrue(app17.isContactCreatedByText("John"));
    }

    @AfterMethod
    public void postCondition() {
        // Чистим данные после теста
        app17.removeContact();
    }
}