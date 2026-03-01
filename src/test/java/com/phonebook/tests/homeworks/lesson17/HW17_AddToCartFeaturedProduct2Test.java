package com.phonebook.tests.homeworks.lesson17;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
Homework – Lesson 17
Project: DemoWebShop

Implement add-to-cart test using TestBase methods.

Test scenario:
1. Login with valid credentials.
2. Add "14.1-inch Laptop" to cart.
3. Verify success popup text:
   "The product has been added to your shopping cart".
4. Open Shopping cart.
5. Verify product is present in cart.
6. Logout.

Structure:
- HW17_TestBase (setup, teardown, reusable methods)
- HW17UserData (credentials)
- HW17_AddToCartFeaturedProduct2Test (test only)
*/

public class HW17_AddToCartFeaturedProduct2Test extends HW17_TestBase {
    @Test
    public void addFeaturedProduct2ToCartTest() {

        step("Open DemoWebShop homepage (https://demowebshop.tricentis.com/)");
        loginWithValidData(HW17UserData.email, HW17UserData.password);
        step("Login submitted: Email: " + HW17UserData.email + "      " + "Password: " + HW17UserData.password);

        loginSuccess("a.ico-logout");
        step("Login success: Logout link visible");

        add2ndProductToShoppingCart();
        step("Clicked Add to cart for 14.1-inch laptop");

        loginSuccess("#bar-notification.bar-notification.success");
        String popupText = driver.findElement(By.cssSelector("#bar-notification p.content")).getText().trim();
        step("Popup shown: " + popupText);

        Assert.assertEquals(popupText, "The product has been added to your shopping cart");
        step("Popup text assert: OK");

        shoppingCartOpenning();
        step("Opened cart (via popup link)");

        Assert.assertTrue(driver.findElements(By.cssSelector("a.product-name[href='/141-inch-laptop']")).size() > 0,
                "Product not found in cart: 14.1-inch Laptop");
        step("Product present in cart: OK");

        logout();
        step("Logout clicked");
    }
}