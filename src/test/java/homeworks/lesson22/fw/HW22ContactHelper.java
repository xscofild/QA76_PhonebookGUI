package homeworks.lesson22.fw;

import homeworks.lesson22.core.HW22BaseHelper;
import homeworks.lesson22.models.HW22Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
 HW22ContactHelper — все действия с контактами: создание, проверка, удаление.

 Принцип: тесты не знают ни одного локатора.
 Если верстка изменится — правим только эти локаторы, тесты не трогаем.
*/
public class HW22ContactHelper extends HW22BaseHelper {

    // CSS-класс карточки контакта в списке.
    // Вынесен в поле — используется в нескольких методах, чтобы не дублировать.
    private final By contactCards = By.cssSelector(".contact-item_card__2SOIM");

    public HW22ContactHelper(WebDriver driver) {
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
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
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
    // [1] name, [2] surname, [3] phone, [4] email, [5] address, [6] description.
    // Если поле в HW22Contact равно null — type() его пропустит (частичное заполнение).
    public void fillContactForm(HW22Contact contact) {
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
