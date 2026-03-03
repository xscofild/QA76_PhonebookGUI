package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
 ContactHelper
 Логика работы с контактами:
 создание, проверка, удаление.
 Используется через ApplicationManager.
*/

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    // Проверка наличия контакта по тексту (например, имя)
    public boolean isContactCreatedByText(String text) {
        List<WebElement> contactsName = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contactsName) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    // Количество карточек контактов
    public int sizeOfContacts() {
        By cards = By.cssSelector(".contact-item_card__2SOIM");
        if (isElementPresent(cards)) {
            return driver.findElements(cards).size();
        }
        return 0;
    }

    // Нажатие Save на форме добавления
    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    // Заполнение формы данными из модели Contact
    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[1]"), contact.getName());
        type(By.xpath("//input[2]"), contact.getSurname());
        type(By.xpath("//input[3]"), contact.getPhoneNumber());
        type(By.xpath("//input[4]"), contact.getEmail());
        type(By.xpath("//input[5]"), contact.getAddress());
        type(By.xpath("//input[6]"), contact.getDescription());
    }

    // Переход на страницу добавления контакта
    public void clickOnAddLink() {
        click(By.cssSelector("[href='/add']"));
    }

    // Удаление первого контакта
    public void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
    }
}