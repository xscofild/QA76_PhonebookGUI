package homeworks.lesson20.tests;

import homeworks.lesson20.core.HW20ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

/*
 HW20TestBase — базовый класс для всех тестов lesson20.

 Главное что добавлено (цель ДЗ Lesson 20):

 1. Logger — пишет логи в консоль и в файл (через logback-test.xml).
    Видим: когда тест стартовал, прошёл или упал.

 2. Скриншот при падении — если тест упал, @AfterMethod
    автоматически делает скриншот и пишет путь в лог.

 3. @BeforeSuite / @AfterSuite вместо @BeforeMethod / @AfterMethod
    для запуска браузера — один браузер на все тесты, не 4 раза.
*/
public class HW20TestBase {

    // static — один браузер на всю suite.
    // System.getProperty("browser", "chrome") — можно передать -Pbrowser=firefox из Gradle.
    protected static HW20ApplicationManager app =
            new HW20ApplicationManager(
                    System.getProperty("browser", Browser.CHROME.browserName())
            );

    Logger logger = LoggerFactory.getLogger(HW20TestBase.class);

    // Логируем имя теста и его параметры перед стартом.
    @BeforeMethod
    public void startTest(Method method, Object[] params) {
        logger.info("▶ Start: {} with data: {}", method.getName(), Arrays.asList(params));
    }

    // Запускаем браузер один раз перед всеми тестами.
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    // После каждого теста: логируем результат.
    // Если упал — делаем скриншот и пишем путь к файлу в лог.
    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("✔ Test passed: {}", result.getMethod().getMethodName());
        } else {
            logger.error("✘ FAILED: {} | Screenshot: {}",
                    result.getMethod().getMethodName(),
                    app.getItem().takeScreenshot());
        }
        logger.info("▶ End: {}", result.getMethod().getMethodName());
        logger.info("──────────────────────────────────────────────");
    }

    // Закрываем браузер один раз после всех тестов.
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}
