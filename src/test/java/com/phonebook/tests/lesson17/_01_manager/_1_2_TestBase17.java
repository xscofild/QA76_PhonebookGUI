package com.phonebook.tests.lesson17._01_manager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/*
 TestBase17 — базовый класс для всех тестов.

 Отвечает за:
 - запуск браузера перед каждым тестом
 - остановку браузера после теста

 Все тесты наследуются от этого класса.
*/

public class _1_2_TestBase17 {

    // Общий ApplicationManager для тестов.
    // Через него управляется WebDriver.
    protected static _1_1_ApplicationManager17 app17 = new _1_1_ApplicationManager17();

    @BeforeMethod
    public void setUp() {

        // Выполняется перед каждым тестом.
        // Запускает браузер и открывает приложение.
        app17.init();
    }

    @AfterMethod(enabled = false)
    public void tearDown() {

        // Выполняется после каждого теста.
        // Сейчас отключено (enabled = false).
        // В рабочей версии должно быть включено, чтобы браузер закрывался.
        app17.stop();
    }
}