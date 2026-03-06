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
 DeleteContactTests — тест удаления контакта.

 Паттерн: Arrange → Act → Assert
   Arrange (@BeforeMethod): логин + создание контакта
   Act     (@Test):         запоминаем размер → удаляем
   Assert  (@Test):         размер уменьшился на 1

 Не полагаемся на конкретное число контактов — сравниваем до/после.
 Это делает тест независимым от количества данных в системе.
*/
public class DeleteContactTests extends TestBase {

    // Предусловие: пользователь авторизован + контакт создан (есть что удалять).
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

        app.getContact().removeContact();

        // pause(400) — SPA асинхронно обновляет список после удаления.
        // Без паузы sizeOfContacts() может посчитать старые карточки до ре-рендера.
        // Здесь нет явного DOM-события для ожидания, поэтому исключение из правила.
        app.getContact().pause(400);

        int sizeAfter = app.getContact().sizeOfContacts();

        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }
}
