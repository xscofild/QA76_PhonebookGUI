package homeworks.lesson20.tests;

import homeworks.lesson20.models.HW20User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HW20NewUserRegisterTests extends HW20TestBase {

    @Test
    public void newUserRegisterTest() {
        HW20User user = new HW20User()
                .setFirstName("Serdar")
                .setLastName("Kerimov")
                .setEmail("test" + System.currentTimeMillis() + "@mail.com")
                .setPassword("Password123!");

        app.getUser().register(user);
        Assert.assertTrue(app.getUser().isLoggedIn());
    }
}
