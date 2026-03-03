package homeworks.lesson19.tests;

import homeworks.lesson19.data.HW19UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HW19ExistingUserRegisterTests extends HW19TestBase {

    @Test
    public void existingUserLoginTest() {
        app.getUser().login(HW19UserData.VALID_EMAIL, HW19UserData.VALID_PASSWORD);
        Assert.assertTrue(app.getUser().isLoggedIn());
    }
}