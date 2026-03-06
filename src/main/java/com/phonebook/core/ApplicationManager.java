package com.phonebook.core;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomePageHelper;
import com.phonebook.fw.UserHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

// Page Object entry point — единственный класс, знающий о WebDriver.
// Создаёт и хранит helper-ы, тестам отдаёт через геттеры.
public class ApplicationManager {

    private final String browser;
    protected WebDriver driver;

    private UserHelper user;
    private ContactHelper contact;
    private HomePageHelper homePage;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    // Генерация уникального email для тестов регистрации.
    public static String getRandomEmail() {
        char[] chars = "0123456789abcdef".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[8];
        for (int i = 0; i < result.length; i++) {
            result[i] = chars[random.nextInt(chars.length)];
        }
        return new String(result) + "@test.com";
    }

    public void init() {
        System.err.close();

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        user     = new UserHelper(driver);
        contact  = new ContactHelper(driver);
        homePage = new HomePageHelper(driver);
    }

    public UserHelper getUser()         { return user; }
    public ContactHelper getContact()   { return contact; }
    public HomePageHelper getHomePage() { return homePage; }

    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
