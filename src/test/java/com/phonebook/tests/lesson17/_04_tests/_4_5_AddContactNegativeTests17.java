/*
package com.phonebook.tests.lesson17._04_tests;

import com.phonebook.tests.lesson17._01_manager._1_2_TestBase17;
import com.phonebook.tests.lesson17._03_model._3_2_Contact17;
import com.phonebook.tests.lesson17._03_model._3_4_ContactData17;
import com.phonebook.tests.lesson17._03_model._3_1_User17;
import com.phonebook.tests.lesson17._03_model._3_3_UserData17;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

*/
/*
 AddContactNegativeTests17 — негативные сценарии добавления контакта.

 Проверяет, что при невалидных данных контакт не создаётся
 и система показывает ошибку.
*//*


public class _4_5_AddContactNegativeTests17 extends _1_2_TestBase17 {

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
    public void addContactWithInvalidPhoneNumber() {

        // Переход на страницу добавления
        app17.clickOnAddLink();

        // Заполнение формы с невалидным номером телефона
        app17.fillContactForm(new _3_2_Contact17()
                .setName(_3_4_ContactData17.name)
                .setSurname(_3_4_ContactData17.surname)
                .setPhoneNumber("1-99-666-4444")
                .setEmail(_3_4_ContactData17.email)
                .setAddress(_3_4_ContactData17.address)
                .setDescription(_3_4_ContactData17.description));

        app17.clickOnSaveButton();

        // Ожидаем ошибку (alert)
        Assert.assertTrue(app17.isAlertPresent());
    }
}*/
