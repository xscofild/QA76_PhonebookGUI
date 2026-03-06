package com.phonebook.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomePageHelper;
import com.phonebook.fw.UserHelper;

import java.time.Duration;

/*
 ApplicationManager — точка входа в фреймворк (Page Object паттерн).

 Единственный класс, который знает о браузере и WebDriver.
 Создаёт и хранит все helper-ы, тестам отдаёт их через геттеры.
 Тестовой логики не содержит — только инфраструктура.

 Жизненный цикл:
   @BeforeSuite → init()  — запускает браузер, открывает сайт
   @AfterSuite  → stop()  — закрывает браузер

 Выбор браузера через Gradle (передаётся в System.getProperty):
   gradlew regression                   → chrome (по умолчанию)
   gradlew regression -Pbrowser=firefox → firefox
*/
public class ApplicationManager {

    private final String browser;
    protected WebDriver driver;

    // Helper-ы — слой между тестами и Selenium.
    // Тесты не трогают driver напрямую, только через helper-ы.
    private UserHelper user;
    private ContactHelper contact;
    private HomePageHelper homePage;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        // Подавляем лишний вывод WebDriverManager в stderr консоли.
        System.err.close();

        // WebDriverManager автоматически скачивает нужную версию драйвера.
        // Без него — пришлось бы вручную следить за версией chromedriver.
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            throw new IllegalArgumentException("Unknown browser: " + browser);
        }

        driver.get("https://telranedu.web.app");
        driver.manage().window().maximize();

        // implicitlyWait — глобальное ожидание элемента перед NoSuchElementException.
        // 5 сек достаточно для большинства элементов этого SPA.
        // Для нестабильных элементов — используй явные ожидания (WebDriverWait) в helper-ах.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Инициализируем helper-ы один раз — они живут всю suite.
        user     = new UserHelper(driver);
        contact  = new ContactHelper(driver);
        homePage = new HomePageHelper(driver);
    }

    // Геттеры — единственный способ для тестов получить доступ к helper-ам.
    public UserHelper getUser()         { return user; }
    public ContactHelper getContact()   { return contact; }
    public HomePageHelper getHomePage() { return homePage; }

    public void stop() {
        // Проверка на null — защита если init() не дошёл до создания driver.
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
