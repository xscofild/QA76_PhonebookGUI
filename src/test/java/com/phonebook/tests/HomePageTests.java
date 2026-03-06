package com.phonebook.tests;

import com.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 HomePageTests — проверка доступности главной страницы.

 Входит в smoke-группу (groups = "smoky").
 Запускается через: gradlew smoky (groups.xml)

 Smoke-тест — самый быстрый индикатор: если главная не открывается,
 нет смысла запускать остальные тесты.
*/
public class HomePageTests extends TestBase {

    // Если главная страница уже открыта — ничего не делаем.
    // Если нет — кликаем на ссылку Home в навигации.
    @BeforeMethod
    public void ensureHomePageIsOpen() {
        if (!app.getHomePage().isHomeComponentPresent()) {
            app.getHomePage().clickOnHomeLink();
        }
    }

    @Test(groups = "smoky")
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
    }
}
