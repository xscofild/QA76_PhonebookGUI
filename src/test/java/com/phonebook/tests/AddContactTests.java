package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.core.TestBase;
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

// Позитивные тесты добавления контакта.
// Источники данных: ContactData.java / @DataProvider / ContactData.csv
public class AddContactTests extends TestBase {

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

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getContact().removeContact();
    }

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

    // ─── DataProvider: данные прямо в коде ───────────────────────────────────

    @DataProvider
    public Iterator<Object[]> addNewContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Oliver", "Kan", "1234567890",   "test1@gm.com", "Berlin",  "QA"});
        list.add(new Object[]{"Lev",    "Kan", "12345678901",  "test2@gm.com", "TelAviv", "QA"});
        list.add(new Object[]{"Irina",  "Kan", "123456789012", "test3@gm.com", "Haifa",   "QA"});
        return list.iterator();
    }

    @Test(dataProvider = "addNewContact")
    public void addContactFromDataProviderTest(String name, String lastName, String phone,
                                               String email, String address, String description) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(name).setSurname(lastName).setPhoneNumber(phone)
                .setEmail(email).setAddress(address).setDescription(description));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreatedByName(name));
    }

    // ─── DataProvider: данные из CSV файла ───────────────────────────────────

    @DataProvider
    public Iterator<Object[]> addNewContactFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/ContactData.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact()
                    .setName(split[0]).setSurname(split[1]).setPhoneNumber(split[2])
                    .setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    // Проверяем по телефону — он уникальнее имени.
    @Test(dataProvider = "addNewContactFromCsv")
    public void addContactFromCsvTest(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreatedByPhone(contact.getPhoneNumber()));
    }
}
