package homeworks.lesson20.fw;

import homeworks.lesson20.core.HW20BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HW20ItemHelper extends HW20BaseHelper {

    public HW20ItemHelper(WebDriver driver) {
        super(driver);
    }

    private final By laptopAddToCart =
            By.xpath("//a[text()='14.1-inch Laptop']/ancestor::div[@class='details']//input[@value='Add to cart']");
    private final By successNotification =
            By.cssSelector("#bar-notification.bar-notification.success");
    private final By cartLinkInPopup =
            By.cssSelector("#bar-notification a[href='/cart']");
    private final By laptopInCart =
            By.xpath("//a[@class='product-name' and text()='14.1-inch Laptop']");
    private final By removeCheckbox = By.name("removefromcart");
    private final By updateCartBtn  = By.name("updatecart");

    public void addLaptopToCart() {
        click(laptopAddToCart);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(successNotification));
    }

    public void openCartFromPopup() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cartLinkInPopup));
        click(cartLinkInPopup);
    }

    public boolean isLaptopPresentInCart() {
        return isElementPresent(laptopInCart);
    }

    public void removeProductFromCart() {
        if (isElementPresent(removeCheckbox)) {
            click(removeCheckbox);
            click(updateCartBtn);
        }
    }
}
