package homeworks.lesson20.tests;

import homeworks.lesson20.data.HW20UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HW20ExistingUserRegisterTests extends HW20TestBase {

    @Test
    public void existingUserLoginTest() {
        app.getUser().login(HW20UserData.VALID_EMAIL, HW20UserData.VALID_PASSWORD);
        Assert.assertTrue(app.getUser().isLoggedIn());
    }
}
