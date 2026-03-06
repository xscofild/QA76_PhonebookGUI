package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.User;
import com.phonebook.models.Contact;
import com.phonebook.data.UserData;
import com.phonebook.data.ContactData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 AddContactTests — позитивные тесты добавления контакта.

 Три источника тестовых данных:
   1. ContactData.java    — статические константы (один контакт)
   2. @DataProvider       — массив данных прямо в коде (3 контакта)
   3. ContactData.csv     — данные из файла (масштабируемо, не нужно перекомпилировать)

 Структура каждого теста:
   @BeforeMethod → логин
   @Test         → создать контакт → проверить
   @AfterMethod  → удалить контакт (чистим за собой)
*/
public class AddContactTests extends TestBase {

    // alwaysRun = true — выполняется даже если @Test упал с ошибкой конфигурации.
    @BeforeMethod(alwaysRun = true)
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

    // groups = "smoky" — тест попадает в smoke-набор (groups.xml / gradlew smoky).
    // Smoke — минимальный набор тестов для быстрой проверки что система работает.
    @Test(groups = "smoky")
    public void addContactPositiveTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.name)
                .setSurname(ContactData.lastName)
                .setPhoneNumber(ContactData.phone)
                .setEmail(ContactData.email)
                .setAddress(ContactData.address)
                .setDescription(ContactData.description));
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactCreatedByName(ContactData.name));
    }

    // Удаляем созданный контакт — не загрязняем данные для следующих тестов.
    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getContact().removeContact();
    }

    // ─── DataProvider: данные прямо в коде ───────────────────────────────────

    // @DataProvider возвращает Iterator<Object[]> — каждый Object[] = один запуск теста.
    // TestNG вызывает тест addContactPositiveFromDataProviderTest столько раз, сколько строк.
    @DataProvider
    public Iterator<Object[]> addNewContact() {
        List<Object[]> list = new ArrayList<>();

        // Формат: name, lastName, phone, email, address, description
        list.add(new Object[]{"Kris", "Tomash", "1234567890",   "test1@gm.com", "TelAviv", "QA"});
        list.add(new Object[]{"Lev",  "Tomash", "12345678910",  "test2@gm.com", "TelAviv", "QA"});
        list.add(new Object[]{"Iris", "Tomash", "123456789112", "test3@gm.com", "TelAviv", "QA"});

        return list.iterator();
    }

    // dataProvider = "addNewContact" — ссылается на метод выше по имени.
    // Параметры метода соответствуют элементам Object[] из DataProvider.
    @Test(dataProvider = "addNewContact")
    public void addContactPositiveFromDataProviderTest(String name,
                                                       String lastName,
                                                       String phone,
                                                       String email,
                                                       String address,
                                                       String description) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setSurname(lastName)
                .setPhoneNumber(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactCreatedByName(name));
    }

    // ─── DataProvider: данные из CSV файла ───────────────────────────────────

    // Читает src/test/resources/ContactData.csv строку за строкой.
    // Формат CSV: name,surname,phone,email,address,description (без заголовка).
    // Преимущество: добавляешь строку в CSV → новый тест без изменения кода.
    @DataProvider
    public Iterator<Object[]> addNewContactFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/ContactData.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{
                    new Contact()
                            .setName(split[0])
                            .setSurname(split[1])
                            .setPhoneNumber(split[2])
                            .setEmail(split[3])
                            .setAddress(split[4])
                            .setDescription(split[5])
            });
            line = reader.readLine();
        }
        return list.iterator();
    }

    // Здесь Contact-объект уже готов — DataProvider создал его из CSV.
    // Проверяем по телефону — он уникальнее имени (меньше вероятность совпадения).
    @Test(dataProvider = "addNewContactFromCSV")
    public void addContactPositiveFromCSVTest(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactCreatedByPhone(contact.getPhoneNumber()));
    }
}
