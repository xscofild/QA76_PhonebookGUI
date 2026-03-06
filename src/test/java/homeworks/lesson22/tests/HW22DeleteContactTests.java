package homeworks.lesson22.tests;

import homeworks.lesson22.core.HW22TestBase;
import homeworks.lesson22.data.HW22ContactData;
import homeworks.lesson22.data.HW22UserData;
import homeworks.lesson22.models.HW22Contact;
import homeworks.lesson22.models.HW22User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 HW22DeleteContactTests — тест удаления контакта.

 Паттерн: Arrange → Act → Assert
   Arrange (@BeforeMethod): логин + создание контакта
   Act     (@Test):         запоминаем размер → удаляем
   Assert  (@Test):         размер уменьшился на 1

 Не полагаемся на конкретное число контактов — сравниваем до/после.
 Это делает тест независимым от количества данных в системе.
*/
public class HW22DeleteContactTests extends HW22TestBase {

    // Предусловие: пользователь авторизован + контакт создан (есть что удалять).
    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new HW22User()
                .setEmail(HW22UserData.email)
                .setPassword(HW22UserData.password));
        app.getUser().clickOnLoginButton();

        // Создаём контакт в @BeforeMethod — чтобы всегда было что удалять.
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new HW22Contact()
                .setName(HW22ContactData.name)
                .setSurname(HW22ContactData.lastName)
                .setPhoneNumber(HW22ContactData.phone)
                .setEmail(HW22ContactData.email)
                .setAddress(HW22ContactData.address)
                .setDescription(HW22ContactData.description));
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void deleteContactTest() {
        int sizeBefore = app.getContact().sizeOfContacts();

        app.getContact().removeContact();

        // pause(400) — SPA асинхронно обновляет список после удаления.
        // Без паузы sizeOfContacts() может посчитать старые карточки до ре-рендера.
        app.getContact().pause(400);

        int sizeAfter = app.getContact().sizeOfContacts();

        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }
}
