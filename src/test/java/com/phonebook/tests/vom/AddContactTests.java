package com.phonebook.tests.vom;

import com.phonebook.tests.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.models.Contact;
import com.phonebook.data.UserData;
import com.phonebook.data.ContactData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 AddContactTests — позитивный сценарий добавления контакта.

 Проверяет, что контакт сохраняется и появляется в списке.
*/

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        // Авторизация перед тестом

        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {

        // Добавление контакта с валидными данными
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.name)
                .setSurname(ContactData.lastName)
                .setPhoneNumber(ContactData.phone)
                .setEmail(ContactData.email)
                .setAddress(ContactData.address)
                .setDescription(ContactData.description));

        app.getContact().clickOnSaveButton();

        // Проверяем появление контакта в списке (по имени)
        Assert.assertTrue(app.getContact().isContactCreatedByText(ContactData.name));
    }

    @AfterMethod
    public void postCondition() {
        // Чистим данные после теста
        app.getContact().removeContact();
    }
}

