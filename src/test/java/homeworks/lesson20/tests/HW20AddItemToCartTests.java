package homeworks.lesson20.tests;

import homeworks.lesson20.data.HW20UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HW20AddItemToCartTests extends HW20TestBase {

    @Test
    public void addItemToCartTest() {
        app.getUser().login(HW20UserData.VALID_EMAIL, HW20UserData.VALID_PASSWORD);

        app.getItem().addLaptopToCart();
        app.getItem().openCartFromPopup();

        Assert.assertTrue(app.getItem().isLaptopPresentInCart());

        app.getUser().logout();
    }
}
