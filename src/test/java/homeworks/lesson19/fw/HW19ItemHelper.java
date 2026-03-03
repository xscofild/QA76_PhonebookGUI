package homeworks.lesson19.fw;

import homeworks.lesson19.core.HW19BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HW19ItemHelper extends HW19BaseHelper {

    public HW19ItemHelper(WebDriver driver) {
        super(driver);
    }

    // Локаторы
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

    // Добавление ноутбука в корзину
    public void addLaptopToCart() {
        click(laptopAddToCart);
        // Ждём появления popup уведомления
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions
                        .visibilityOfElementLocated(successNotification));
    }

    // Переход в корзину через popup уведомление
    public void openCartFromPopup() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions
                        .elementToBeClickable(cartLinkInPopup));
        click(cartLinkInPopup);
    }

    // Проверка наличия ноутбука в корзине
    public boolean isLaptopPresentInCart() {
        return isElementPresent(laptopInCart);
    }

    // Очистка корзины (удаление товара)
    public void removeProductFromCart() {
        if (isElementPresent(removeCheckbox)) {
            click(removeCheckbox);
            click(updateCartBtn);
        }
    }
}