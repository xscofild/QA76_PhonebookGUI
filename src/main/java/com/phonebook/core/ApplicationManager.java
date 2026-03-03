package com.phonebook.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomePageHelper;
import com.phonebook.fw.UserHelper;

import java.time.Duration;

/*
 ApplicationManager
 Центральная точка управления приложением.

 Ответственность:
 - инициализация WebDriver
 - создание helper-объектов
 - корректное завершение сессии

 Не содержит:
 - тестовой логики
 - локаторов
 - UI-действий
*/

public class ApplicationManager {

    // Общий драйвер для всего фреймворка
    protected WebDriver driver;

    // Helper-слой (работа со страницами)
    private UserHelper user;
    private ContactHelper contact;
    private HomePageHelper homePage;

    public void init() {

        // Автоматически скачивает и подключает нужную версию chromedriver
        WebDriverManager.chromedriver().setup();

        // Создание экземпляра браузера
        driver = new ChromeDriver();

        // Базовый URL приложения
        driver.get("https://telranedu.web.app");

        // Работаем всегда в maximized режиме — меньше проблем с адаптивной версткой
        driver.manage().window().maximize();

        // Базовое ожидание для поиска элементов
        // Использовать аккуратно — при росте проекта лучше перейти на explicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Инициализация слоя взаимодействия (Page/Helper abstraction)
        user = new UserHelper(driver);
        contact = new ContactHelper(driver);
        homePage = new HomePageHelper(driver);
    }

    // Геттеры — точка доступа тестов к helper-слою

    public UserHelper getUser() {
        return user;
    }

    public ContactHelper getContact() {
        return contact;
    }

    public HomePageHelper getHomePage() {
        return homePage;
    }

    public void stop() {
        // Полное завершение сессии WebDriver
        driver.quit();
    }
}