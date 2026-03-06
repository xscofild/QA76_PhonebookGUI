package com.phonebook.core;

import com.phonebook.core.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.Arrays;

// Базовый класс для всех тестов.
// Жизненный цикл: @BeforeSuite → @BeforeMethod → @Test → @AfterMethod → @AfterSuite
public class TestBase {

    // Один экземпляр на всю suite — браузер открывается один раз.
    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected SoftAssert softAssert;

    @BeforeMethod
    public void startTest(Method method, Object[] p) {
        logger.info("▶ Start: {} | data: {}", method.getName(), Arrays.asList(p));
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        app.init();
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("✔ PASSED: {}", result.getMethod().getMethodName());
        } else {
            logger.error("✘ FAILED: {} | Screenshot: {}",
                    result.getMethod().getMethodName(),
                    app.getContact().takeScreenshot());
        }
        logger.info("──────────────────────────────────────────────");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeGroups("smoky")
    public void setUpSmokyGroup() {
        logger.info("▶ Start smoky group");
    }

    @AfterGroups("smoky")
    public void stopSmokyGroup() {
        logger.info("▶ Stop smoky group");
    }
}
