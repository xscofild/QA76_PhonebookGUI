package homeworks.lesson19.tests;

import homeworks.lesson19.models.HW19User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HW19NewUserRegisterTests extends HW19TestBase {

    @Test
    public void newUserRegisterTest() {
        HW19User user = new HW19User()
                .setFirstName("Serdar")
                .setLastName("Kerimov")
                .setEmail("test" + System.currentTimeMillis() + "@mail.com")
                .setPassword("Password123!");

        app.getUser().register(user);
        Assert.assertTrue(app.getUser().isLoggedIn());
    }
}