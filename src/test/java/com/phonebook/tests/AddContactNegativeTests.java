package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.models.Contact;
import com.phonebook.data.UserData;
import com.phonebook.data.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 AddContactNegativeTests — негативные сценарии добавления контакта.

 Проверяет, что при невалидных данных контакт не создаётся
 и система показывает ошибку (alert).
*/
public class AddContactNegativeTests extends TestBase {

    // Гарантируем чистое состояние: пользователь авторизован перед тестом.
    @BeforeMethod
    public void precondition() {
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
    public void addContactWithInvalidPhoneNumber() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.name)
                .setSurname(ContactData.lastName)
                .setPhoneNumber("1-99-666-4444") // невалидный формат номера
                .setEmail(ContactData.email)
                .setAddress(ContactData.address)
                .setDescription(ContactData.description));
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isAlertPresent());
    }
}