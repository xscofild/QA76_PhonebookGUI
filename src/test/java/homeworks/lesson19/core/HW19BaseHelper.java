package homeworks.lesson19.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HW19BaseHelper {
    protected WebDriver driver;

    public HW19BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public boolean isAlertPresent() {
        try {
            org.openqa.selenium.Alert alert = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
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
}