package com.phonebook.tests.lesson16;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests16 extends TestBase16 {
    // Тесты главной страницы. Наследуем BaseTest (там открытие сайта и driver).

    @Test
    public void isHomeComponentPresentTest() {
        // Проверяем, что Home компонент присутствует на странице.
        Assert.assertTrue(isHomeComponentPresent());
    }

}