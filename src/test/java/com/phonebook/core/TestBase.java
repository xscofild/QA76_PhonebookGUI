package com.phonebook.core;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

/*
 TestBase — базовый класс для всех тестов.

 Инициализирует браузер перед каждым тестом и закрывает после.
 Браузер берётся из Gradle: gradlew regression -Pbrowser=firefox
 По умолчанию — chrome.
*/
public class TestBase {

    protected static ApplicationManager app =
            new ApplicationManager(
                    System.getProperty("browser", Browser.CHROME.browserName())
            );

    protected SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}