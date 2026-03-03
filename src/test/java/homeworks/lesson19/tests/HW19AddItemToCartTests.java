package homeworks.lesson19.tests;

import homeworks.lesson19.data.HW19UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HW19AddItemToCartTests extends HW19TestBase {

    @Test
    public void addItemToCartTest() {
        app.getUser().login(HW19UserData.VALID_EMAIL, HW19UserData.VALID_PASSWORD);

        app.getItem().addLaptopToCart();
        app.getItem().openCartFromPopup();

        Assert.assertTrue(app.getItem().isLaptopPresentInCart());

        app.getUser().logout();
    }
}