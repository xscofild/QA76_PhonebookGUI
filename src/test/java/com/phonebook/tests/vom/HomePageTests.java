/*
package com.phonebook.tests.lesson18._04_tests;*/
package com.phonebook.tests.vom;

import com.phonebook.tests.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 HomePageTests — проверки главной страницы.

 Проверяет, что основной компонент Home отображается после открытия сайта.

*/

public class HomePageTests extends TestBase {

    @BeforeMethod
    public void ensureHomePageIsOpen() {
        if (!app.getHomePage().isHomeComponentPresent()) {
            app.getHomePage().clickOnHomeLink();
        }
    }

    @Test
    public void isHomeComponentPresentTest() {

        // Проверяем наличие ключевого элемента главной страницы
        Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
    }
}
