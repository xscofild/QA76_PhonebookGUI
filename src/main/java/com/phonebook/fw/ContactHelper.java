package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
 ContactHelper — все действия с контактами: создание, проверка, удаление.

 Принцип: тесты не знают ни одного локатора.
 Если верстка изменится — правим только эти локаторы, тесты не трогаем.
*/
public class ContactHelper extends BaseHelper {

    // CSS-класс карточки контакта в списке.
    // Вынесен в поле — используется в нескольких методах, чтобы не дублировать.
    private final By contactCards = By.cssSelector(".contact-item_card__2SOIM");

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    // Проверяет наличие контакта по имени (тег h2 в карточке).
    public boolean isContactCreatedByName(String text) {
        return verifyText(text, By.cssSelector("h2"));
    }

    // Проверяет наличие контакта по номеру телефона (тег h3 в карточке).
    public boolean isContactCreatedByPhone(String text) {
        return verifyText(text, By.cssSelector("h3"));
    }

    // Универсальный поиск текста среди всех элементов с данным локатором.
    // Перебирает все элементы — не падает если элементов нет.
    public boolean verifyText(String text, By locator) {
        List<WebElement> names = driver.findElements(locator);
        for (WebElement element : names) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает количество карточек контактов на странице.
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

    // Порядок вызовов type() соответствует порядку input-полей на форме добавления:
    // [0] name, [1] surname, [2] phone, [3] email, [4] address, [5] description.
    // Если поле в Contact равно null — type() его пропустит (поддержка частичного заполнения).
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

    // Кликает первую карточку в списке → открывает детали → нажимает Remove.
    // Используется для удаления контакта в @AfterMethod / @BeforeMethod тестов.
    public void removeContact() {
        click(contactCards);
        click(By.xpath("//button[.='Remove']"));
    }
}
