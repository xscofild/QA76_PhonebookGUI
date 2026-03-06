package homeworks.lesson20.core;

import homeworks.lesson20.fw.HW20ItemHelper;
import homeworks.lesson20.fw.HW20UserHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/*
 HW20ApplicationManager — точка входа в фреймворк для lesson20.

 Создаёт WebDriver, инициализирует helper-ы, завершает сессию.
 Браузер передаётся через System.getProperty("browser") из HW20TestBase.
*/
public class HW20ApplicationManager {

    String browser;
    protected WebDriver driver;

    private HW20UserHelper user;
    private HW20ItemHelper item;

    public HW20ApplicationManager(String browser) {
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

        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        user = new HW20UserHelper(driver);
        item = new HW20ItemHelper(driver);
    }

    public HW20UserHelper getUser() { return user; }
    public HW20ItemHelper getItem() { return item; }

    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
