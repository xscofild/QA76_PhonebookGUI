package com.phonebook.tests.vom;

import com.phonebook.tests.core.TestBase;
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
 и система показывает ошибку.
*/

public class AddContactNegativeTests extends TestBase {

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
    public void addContactWithInvalidPhoneNumber() {

        // Переход на страницу добавления
        app.getContact().clickOnAddLink();

        // Заполнение формы с невалидным номером телефона
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.name)
                .setSurname(ContactData.lastName)
                .setPhoneNumber("1-99-666-4444")
                .setEmail(ContactData.email)
                .setAddress(ContactData.address)
                .setDescription(ContactData.description));

        app.getContact().clickOnSaveButton();

        // Ожидаем ошибку (alert)
        Assert.assertTrue(app.getContact().isAlertPresent());
    }
}

