package com.phonebook.tests.lesson17._04_tests;

import com.phonebook.tests.lesson17._01_manager._1_2_TestBase17;
import com.phonebook.tests.lesson17._03_model._3_2_Contact17;
import com.phonebook.tests.lesson17._03_model._3_4_ContactData17;
import com.phonebook.tests.lesson17._03_model._3_1_User17;
import com.phonebook.tests.lesson17._03_model._3_3_UserData17;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 DeleteContactTests17 — тест удаления контакта.

 Предусловие: пользователь авторизован и в списке есть хотя бы один контакт.
 Проверка: после удаления количество контактов уменьшается на 1.
*/

public class _4_6_DeleteContactTests17 extends _1_2_TestBase17 {

    @BeforeMethod
    public void precondition() {

        // 1) Логин
        app17.clickOnLoginLink();
        app17.fillLoginRegisterForm(new _3_1_User17()
                .setEmail(_3_3_UserData17.email)
                .setPassword(_3_3_UserData17.password));
        app17.clickOnLoginButton();

        // 2) Создаём контакт, чтобы было что удалять
        app17.clickOnAddLink();
        app17.fillContactForm(new _3_2_Contact17()
                .setName(_3_4_ContactData17.name)
                .setSurname(_3_4_ContactData17.surname)
                .setPhoneNumber(_3_4_ContactData17.phoneNumber)
                .setEmail(_3_4_ContactData17.email)
                .setAddress(_3_4_ContactData17.address)
                .setDescription(_3_4_ContactData17.description));
        app17.clickOnSaveButton();
    }

    @Test
    public void deleteContactTest() {

        int sizeBefore = app17.sizeOfContacts();

        // Удаляем контакт (первый в списке)
        app17.removeContact();

        // Временная пауза, чтобы UI успел обновиться
        app17.pause(400);

        int sizeAfter = app17.sizeOfContacts();

        // Количество контактов должно уменьшиться на 1
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }
}