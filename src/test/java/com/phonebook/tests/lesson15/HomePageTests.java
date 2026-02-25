package com.phonebook.tests.lesson15;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{
    // Тесты главной страницы. Наследуем BaseTest (там открытие сайта и driver).

    @Test
    public void isHomeComponentPresentTest() {
        // Проверяем, что Home компонент присутствует на странице.
        Assert.assertTrue(isHomeComponentPresent());
    }

}