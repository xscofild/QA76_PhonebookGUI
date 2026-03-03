package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
 ContactHelper — действия с контактами.

 Создание, проверка наличия, удаление.
 Локаторы инкапсулированы здесь — тесты не знают о By/xpath/css.
*/
public class ContactHelper extends BaseHelper {

    // CSS-класс карточки контакта. Если верстка изменится — правим только здесь.
    private final By contactCards = By.cssSelector(".contact-item_card__2SOIM");

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    // Ищет совпадение по тексту среди всех h2 на странице.
    // Используется для проверки что контакт появился после создания.
    public boolean isContactCreatedByText(String text) {
        List<WebElement> names = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : names) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает 0 если карточек нет — не бросает исключение.
    public int sizeOfContacts() {
        if (isElementPresent(contactCards)) {
            return driver.findElements(contactCards).size();
        }
        return 0;
    }

    public void clickOnAddLink() {
        click(By.cssSelector("[href='/add']"));
    }

    // Порядок полей соответствует порядку input-ов на форме добавления.
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

    // Кликает первую карточку и удаляет контакт.
    // Используется для очистки данных после теста.
    public void removeContact() {
        click(contactCards);
        click(By.xpath("//button[.='Remove']"));
    }
}