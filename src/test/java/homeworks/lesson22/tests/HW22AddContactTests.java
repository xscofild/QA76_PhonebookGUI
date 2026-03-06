package homeworks.lesson22.tests;

import homeworks.lesson22.core.HW22TestBase;
import homeworks.lesson22.data.HW22ContactData;
import homeworks.lesson22.data.HW22UserData;
import homeworks.lesson22.models.HW22Contact;
import homeworks.lesson22.models.HW22User;
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
 HW22AddContactTests — позитивные тесты добавления контакта.

 Три источника тестовых данных:
   1. HW22ContactData.java — статические константы (один контакт)
   2. @DataProvider        — массив данных прямо в коде (3 контакта)
   3. ContactData.csv      — данные из файла (масштабируемо, без перекомпиляции)

 Структура каждого теста:
   @BeforeMethod → логин
   @Test         → создать контакт → проверить
   @AfterMethod  → удалить контакт (чистим за собой)
*/
public class HW22AddContactTests extends HW22TestBase {

    // alwaysRun = true — выполняется даже если @Test упал с ошибкой конфигурации.
    @BeforeMethod(alwaysRun = true)
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

    // groups = "smoky" — тест попадает в smoke-набор.
    // Smoke — минимальный набор тестов для быстрой проверки что система работает.
    @Test(groups = "smoky")
    public void addContactPositiveTest() {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new HW22Contact()
                .setName(HW22ContactData.name)
                .setSurname(HW22ContactData.lastName)
                .setPhoneNumber(HW22ContactData.phone)
                .setEmail(HW22ContactData.email)
                .setAddress(HW22ContactData.address)
                .setDescription(HW22ContactData.description));
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactCreatedByName(HW22ContactData.name));
    }

    // Удаляем созданный контакт — не загрязняем данные для следующих тестов.
    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getContact().removeContact();
    }

    // ─── DataProvider: данные прямо в коде ───────────────────────────────────

    // @DataProvider возвращает Iterator<Object[]> — каждый Object[] = один запуск теста.
    @DataProvider
    public Iterator<Object[]> addNewContact() {
        List<Object[]> list = new ArrayList<>();

        // Формат: name, lastName, phone, email, address, description
        list.add(new Object[]{"Serdar",  "Kerimov", "4915123456789",  "serdar@test.com",  "Berlin",   "QA"});
        list.add(new Object[]{"Ali",     "Kerimov", "4915987654321",  "ali@test.com",     "Hamburg",  "Dev"});
        list.add(new Object[]{"Leyla",   "Kerimov", "4915111222333",  "leyla@test.com",   "Munich",   "PM"});

        return list.iterator();
    }

    @Test(dataProvider = "addNewContact")
    public void addContactFromDataProviderTest(String name, String lastName, String phone,
                                               String email, String address, String description) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new HW22Contact()
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
                    new HW22Contact()
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

    // Проверяем по телефону — он уникальнее имени (меньше вероятность совпадения).
    @Test(dataProvider = "addNewContactFromCSV")
    public void addContactFromCSVTest(HW22Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactCreatedByPhone(contact.getPhoneNumber()));
    }
}
