package homeworks.lesson19.tests;

import homeworks.lesson19.core.HW19ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class HW19TestBase {

    protected HW19ApplicationManager app;

    @BeforeMethod
    public void setUp() {
        app = new HW19ApplicationManager("chrome"); // передаём браузер
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        if (app != null) app.stop();
    }
}