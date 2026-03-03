package homeworks.lesson19.core;

import homeworks.lesson19.fw.HW19ItemHelper;
import homeworks.lesson19.fw.HW19UserHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class HW19ApplicationManager {

    String browser;
    protected WebDriver driver;

    private HW19UserHelper user;
    private HW19ItemHelper item;

    public HW19ApplicationManager(String browser) {
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

        user = new HW19UserHelper(driver);
        item = new HW19ItemHelper(driver);
    }

    public HW19UserHelper getUser() {
        return user;
    }

    public HW19ItemHelper getItem() {
        return item;
    }

    public void stop() {
        driver.quit();
    }
}