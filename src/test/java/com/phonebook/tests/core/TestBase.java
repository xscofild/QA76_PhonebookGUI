package com.phonebook.tests.core;

import com.phonebook.core.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/*
 TestBase — базовый класс для всех тестов.

 Отвечает за:
 - запуск браузера перед каждым тестом
 - остановку браузера после теста

 Все тесты наследуются от этого класса.
*/

public class TestBase {

    // Общий ApplicationManager для тестов.
    // Через него управляется WebDriver.
    protected static ApplicationManager app = new ApplicationManager();

    //@BeforeMethod
    @BeforeSuite            // Выполняется один раз перед всеми тестами в классе.
    public void setUp() {

        // Выполняется перед каждым тестом.
        // Запускает браузер и открывает приложение.
        app.init();
    }

    @AfterSuite(enabled = false)   // Выполняется один раз после всех тестов в классе.
    public void tearDown() {

        // Выполняется после каждого теста.
        // Сейчас отключено (enabled = false).
        // В рабочей версии должно быть включено, чтобы браузер закрывался.
        app.stop();
    }
}