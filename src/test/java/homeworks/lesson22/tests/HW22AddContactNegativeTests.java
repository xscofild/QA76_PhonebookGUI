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
 HW22AddContactNegativeTests — негативные тесты добавления контакта.

 Проверяет что система корректно отклоняет невалидные данные.
 При невалидных данных: контакт не создаётся, появляется alert.
*/
public class HW22AddContactNegativeTests extends HW22TestBase {

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
    }

    // Проверяет валидацию формата номера телефона.
    // "123456789" — менее 10 цифр, сервер не принимает.
    // Ожидаем: система показывает alert, контакт не сохраняется.
    @Test
    public void addContactWithInvalidPhoneTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new HW22Contact()
                .setName(HW22ContactData.name)
                .setSurname(HW22ContactData.lastName)
                .setPhoneNumber("123456789")     // невалидный телефон — менее 10 цифр
                .setEmail(HW22ContactData.email)
                .setAddress(HW22ContactData.address)
                .setDescription(HW22ContactData.description));
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isAlertPresent());
    }
}
