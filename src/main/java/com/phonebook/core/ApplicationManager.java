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
 ApplicationManager — точка входа в фреймворк.

 Создаёт WebDriver, инициализирует helper-слой,
 завершает сессию. Тестовой логики не содержит.

 Браузер передаётся через Gradle:
   gradlew regression -Pbrowser=firefox
 По умолчанию — chrome (см. TestBase).
*/
public class ApplicationManager {

    private final String browser;
    protected WebDriver driver;

    private UserHelper user;
    private ContactHelper contact;
    private HomePageHelper homePage;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
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

        // implicitlyWait — минимальное глобальное ожидание.
        // Для динамических элементов используй явные ожидания в helpers.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        user     = new UserHelper(driver);
        contact  = new ContactHelper(driver);
        homePage = new HomePageHelper(driver);
    }

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HomePageHelper getHomePage() {
        return homePage;
    }

    public void stop() {
        driver.quit();
    }
}