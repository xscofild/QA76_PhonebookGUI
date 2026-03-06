package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// Все действия с контактами: создание, поиск, удаление.
// Тесты не знают ни одного локатора — всё здесь.
public class ContactHelper extends BaseHelper {

    private final By contactCards = By.cssSelector(".contact-item_card__2SOIM");

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isContactCreatedByName(String text) {
        return verifyText(text, By.cssSelector("h2"));
    }

    public boolean isContactCreatedByPhone(String text) {
        return verifyText(text, By.cssSelector("h3"));
    }

    public boolean verifyText(String text, By locator) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            if (element.getText().equals(text)) return true;
        }
        return false;
    }

    public int sizeOfContacts() {
        if (isElementPresent(contactCards)) {
            return driver.findElements(contactCards).size();
        }
        return 0;
    }

    public void clickOnAddLink() {
        click(By.cssSelector("[href='/add']"));
    }

    // Порядок input-полей: [1]name [2]surname [3]phone [4]email [5]address [6]description
    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[1]"), contact.getName());
        type(By.xpath("//input[2]"), contact.getSurname());
        type(By.xpath("//input[3]"), contact.getPhoneNumber());
        type(By.xpath("//input[4]"), contact.getEmail());
        type(By.xpath("//input[5]"), contact.getAddress());
        type(By.xpath("//input[6]"), contact.getDescription());
    }

    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    // Клик по первой карточке → открыть детали → Remove.
    public void removeContact() {
        click(contactCards);
        click(By.xpath("//button[.='Remove']"));
    }
}
