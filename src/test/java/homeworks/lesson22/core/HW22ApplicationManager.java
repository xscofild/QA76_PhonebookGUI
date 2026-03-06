package homeworks.lesson22.core;

import homeworks.lesson22.fw.HW22ContactHelper;
import homeworks.lesson22.fw.HW22HomePageHelper;
import homeworks.lesson22.fw.HW22UserHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

/*
 HW22ApplicationManager — точка входа в фреймворк (Page Object паттерн).

 Единственный класс, который знает о браузере и WebDriver.
 Создаёт и хранит все helper-ы, тестам отдаёт их через геттеры.
 Тестовой логики не содержит — только инфраструктура.

 Жизненный цикл:
   @BeforeSuite → init()  — запускает браузер, открывает сайт
   @AfterSuite  → stop()  — закрывает браузер

 Выбор браузера через Gradle (передаётся в System.getProperty):
   gradlew HW22Jenkins                   → firefox (из XML параметра)
   gradlew HW22Jenkins -Pbrowser=chrome  → chrome
*/
public class HW22ApplicationManager {

    private final String browser;
    protected WebDriver driver;

    // Helper-ы — слой между тестами и Selenium.
    // Тесты не трогают driver напрямую, только через helper-ы.
    private HW22UserHelper user;
    private HW22ContactHelper contact;
    private HW22HomePageHelper homePage;

    public HW22ApplicationManager(String browser) {
        this.browser = browser;
    }

    // Генерация уникального email для регистрации новых пользователей.
    // SecureRandom — криптографически стойкий генератор (лучше Random для тестов).
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

        // Подавляем лишний вывод WebDriverManager в stderr консоли.
        System.err.close();

        // WebDriverManager автоматически скачивает нужную версию драйвера.
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Инициализируем helper-ы один раз — они живут всю suite.
        user     = new HW22UserHelper(driver);
        contact  = new HW22ContactHelper(driver);
        homePage = new HW22HomePageHelper(driver);
    }

    // Геттеры — единственный способ для тестов получить доступ к helper-ам.
    public HW22UserHelper getUser()         { return user; }
    public HW22ContactHelper getContact()   { return contact; }
    public HW22HomePageHelper getHomePage() { return homePage; }

    public void stop() {
        // Проверка на null — защита если init() не дошёл до создания driver.
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
