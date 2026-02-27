package com.phonebook.tests.lesson17._01_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/*
 ApplicationManager17 — управляет жизненным циклом браузера.

 Отвечает только за:
 - запуск WebDriver (init)
 - закрытие WebDriver (stop)

 Не содержит тестовой логики.
 Не содержит действий со страницами.
*/

public class _1_1_ApplicationManager17 {

    // Единственный экземпляр WebDriver для теста.
    WebDriver driver;

    public void init() {

        // Автоматическая настройка chromedriver.
        WebDriverManager.chromedriver().setup();

        // Создание браузера.
        driver = new ChromeDriver();

        // Открытие тестируемого приложения.
        driver.get("https://telranedu.web.app");

        // Разворачивание окна.
        driver.manage().window().maximize();

        // Глобальное ожидание элементов (implicit wait).
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void stop() {

        // Закрытие браузера и завершение сессии.
        driver.quit();
    }
}