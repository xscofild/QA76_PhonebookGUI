package homeworks.lesson20.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

/*
 ДЗ Lesson 20 — добавлено: метод takeScreenshot()

 Зачем: при падении теста автоматически делаем скриншот браузера,
 чтобы видеть ЧТО именно было на экране в момент ошибки.
 Вызывается из HW20TestBase в @AfterMethod когда тест упал.
*/
public class HW20BaseHelper {

    protected WebDriver driver;

    public HW20BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public boolean isAlertPresent() {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Делает скриншот браузера и сохраняет в папку screenshots/.
    // Если есть открытый alert — сначала закрывает его (иначе скриншот не получится).
    // Имя файла уникальное через timestamp — старые скриншоты не перезаписываются.
    public String takeScreenshot() {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception ignored) {
        }

        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/hw20-screen-" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return screenshot.getAbsolutePath();
    }
}
