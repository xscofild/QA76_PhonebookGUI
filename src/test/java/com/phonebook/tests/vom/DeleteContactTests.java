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
 DeleteContactTests — тест удаления контакта.

 Предусловие: пользователь авторизован и в списке есть хотя бы один контакт.
 Проверка: после удаления количество контактов уменьшается на 1.
*/

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {

        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        // 1) Логин
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();

        // 2) Создаём контакт, чтобы было что удалять
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.name)
                .setSurname(ContactData.lastName)
                .setPhoneNumber(ContactData.phone)
                .setEmail(ContactData.email)
                .setAddress(ContactData.address)
                .setDescription(ContactData.description));
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void deleteContactTest() {

        int sizeBefore = app.getContact().sizeOfContacts();

        // Удаляем контакт (первый в списке)
        app.getContact().removeContact();

        // Временная пауза, чтобы UI успел обновиться
        app.getContact().pause(400);

        int sizeAfter = app.getContact().sizeOfContacts();

        // Количество контактов должно уменьшиться на 1
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }
}
