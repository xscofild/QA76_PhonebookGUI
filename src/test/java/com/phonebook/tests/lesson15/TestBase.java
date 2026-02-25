package com.phonebook.tests.lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();   // подготовка драйвера
        driver = new ChromeDriver();               // запуск Chrome
        driver.get("https://telranedu.web.app");   // открываем сайт
        driver.manage().window().maximize();       // разворачиваем окно
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // ожидание 10 сек
    }

    public boolean isHomeComponentPresent() {
        // Ищем <h1> внутри второго <div>.
        // findElements не выбрасывает исключение, если элемент не найден.
        // Если список не пустой — элемент есть.
        return driver.findElements(By.xpath("//div[2]//h1")).size() > 0;
    }

    public boolean isElementPresent(By locator) {
        // Универсальный метод проверки наличия элемента по любому локатору.
        return driver.findElements(locator).size() > 0;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {                    // Выполняется после каждого теста
        if (driver != null) {
            driver.quit();                      // Закрываем браузер
        }
    }
}
