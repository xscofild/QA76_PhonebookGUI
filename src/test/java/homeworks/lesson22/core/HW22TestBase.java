package homeworks.lesson22.core;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.Arrays;

/*
 HW22TestBase — базовый класс, от которого наследуются ВСЕ тестовые классы lesson22.

 Порядок выполнения аннотаций TestNG:
   @BeforeSuite   — 1 раз перед всеми тестами  → запускаем браузер
   @BeforeMethod  — перед каждым @Test          → логируем старт
   @Test          — сам тест
   @AfterMethod   — после каждого @Test         → логируем результат / скриншот
   @AfterSuite    — 1 раз после всех тестов     → закрываем браузер

 SoftAssert — собирает все ошибки и кидает их разом в конце теста.
              Нужно вызывать softAssert.assertAll() в конце теста!

 Браузер по умолчанию — chrome.
 Переопределяется через XML: <parameter name="browser" value="firefox"/>
 Или через Gradle: gradlew HW22Jenkins -Pbrowser=chrome
*/
public class HW22TestBase {

    // static — один экземпляр HW22ApplicationManager на всю suite (все тесты).
    // Browser.CHROME.browserName() возвращает строку "chrome".
    protected static HW22ApplicationManager app =
            new HW22ApplicationManager(
                    System.getProperty("browser", Browser.CHROME.browserName())
            );

    // Logger из SLF4J + Logback. Настройка вывода — в logback.xml.
    Logger logger = LoggerFactory.getLogger(HW22TestBase.class);

    protected SoftAssert softAssert;

    // method — объект текущего теста (имя, аннотации).
    // p — параметры теста (из @DataProvider или @Parameters).
    @BeforeMethod
    public void startTest(Method method, Object[] p) {
        logger.info("▶ Start: {} with data: {}", method.getName(), Arrays.asList(p));
    }

    // alwaysRun = true — выполнится даже если тест упал с ошибкой конфигурации.
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
        app.init();
    }

    // ITestResult — объект с результатом теста: passed / failed / skipped.
    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("✔ PASSED: {}", result.getMethod().getMethodName());
        } else {
            // При падении — делаем скриншот и логируем путь к файлу.
            logger.error("✘ FAILED: {} | Screenshot: {}",
                    result.getMethod().getMethodName(),
                    app.getContact().takeScreenshot());
        }
        logger.info("▶ End: {}", result.getMethod().getMethodName());
        logger.info("──────────────────────────────────────────────");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    // groups.xml запускает только тесты с аннотацией @Test(groups = "smoky").
    // @BeforeGroups / @AfterGroups — обёртка вокруг группы, не вокруг каждого теста.
    @BeforeGroups("smoky")
    public void setUpSmokyGroup() {
        logger.info("▶ Start smoky group");
    }

    @AfterGroups("smoky")
    public void stopSmokyGroup() {
        logger.info("▶ Stop smoky group");
    }
}
