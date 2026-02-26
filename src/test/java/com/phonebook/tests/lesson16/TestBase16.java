package com.phonebook.tests.lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class TestBase16 {

    // Общий WebDriver для всех тестов, которые наследуются от этого класса.
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Подготавливаем бинарник драйвера под текущую ОС/версию Chrome.
        WebDriverManager.chromedriver().setup();

        // Стартуем браузер.
        driver = new ChromeDriver();

        // Открываем тестовый сайт.
        driver.get("https://telranedu.web.app");

        // Максимизируем окно (чтобы верстка/элементы не "прыгали" из-за размера).
        driver.manage().window().maximize();

        // Implicit wait: WebDriver будет до 10 сек ждать элементы при findElement/findElements.
        // Важно: смешивать implicit + explicit ожидания нужно осторожно (могут быть лишние задержки).
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isHomeComponentPresent() {
        // Проверяем наличие Home компонента (ищем заголовок h1 в блоке страницы).
        // findElements() НЕ кидает исключение, если ничего не найдено → вернёт пустой список.
        return driver.findElements(By.xpath("//div[2]//h1")).size() > 0;
    }

    public boolean isElementPresent(By locator) {
        // Универсальная проверка существования элемента.
        // Без исключений: 0 элементов → false, >=1 → true.
        return driver.findElements(locator).size() > 0;
    }

    @AfterMethod(enabled = false) // выключено: для лекции/демо (в реальных тестах лучше включать)
    public void tearDown() {
        // Небольшая пауза, чтобы глазами увидеть финальное состояние (не best practice для CI).
        pause(1000);

        // Закрываем браузер и убираем процесс драйвера.
        if (driver != null) driver.quit();
    }

    public void type(By locator, String text) {
        // Пишем текст в поле: клик → очистка → ввод.
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        // Упрощаем тесты: одна точка входа для клика.
        driver.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        // Проверка наличия alert'а через explicit wait.
        // Логика тут спорная: until() по таймауту кидает exception, а не вернёт null.
        /*Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
        return alert != null;*/
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());

        if (alert == null) {
            return false;
        } else {
            return true;
        }
    }


    public boolean isSignOutButtonPresent() {
        // Проверяем, что пользователь залогинен: должна быть кнопка Sign Out.
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    public void clickOnRegistrationButton() {
        // Кнопка Registration на форме логина.
        click(By.name("registration"));
    }

    public void fillLoginRegisterForm(String email, String password) {
        // Заполняем форму логина/регистрации.
        type(By.name("email"), email);
        type(By.name("password"), password);
    }

    public void clickOnLoginLink() {
        // Ссылку "Login" на главной странице.
        click(By.cssSelector("[href='/login']"));
    }

    public void clickOnLoginButton() {
        // Кнопка Login на форме.
        click(By.name("login"));
    }

    public boolean isContactCreatedByText(String text) {
        // Проверяем, появился ли контакт по отображаемому имени (h2).
        List<WebElement> contactsName = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contactsName) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    protected int sizeOfContacts() {
        // Безопасно возвращаем количество карточек контактов.
        // Если на странице нет ни одной карточки — возвращаем 0.
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
        return 0;
    }

    public void pause(int millis) {
        // Техническая пауза (использовать осторожно: лучше ожидания по условиям).
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}