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
 AddContactNegativeTests — негативные тесты добавления контакта.

 Проверяет что система корректно отклоняет невалидные данные.
 При невалидных данных: контакт не создаётся, появляется alert.

 Запускается через: gradlew negative (negative.xml)
*/
public class AddContactNegativeTests extends TestBase {

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

    // Проверяет валидацию формата номера телефона.
    // "1-99-666-4444" — содержит дефисы, которые сервер не принимает.
    // Ожидаем: система показывает alert, контакт не сохраняется.
    @Test
    public void addContactWithInvalidPhoneNumber() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.name)
                .setSurname(ContactData.lastName)
                .setPhoneNumber("1-99-666-4444") // невалидный формат — дефисы не разрешены
                .setEmail(ContactData.email)
                .setAddress(ContactData.address)
                .setDescription(ContactData.description));
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isAlertPresent());
    }
}
