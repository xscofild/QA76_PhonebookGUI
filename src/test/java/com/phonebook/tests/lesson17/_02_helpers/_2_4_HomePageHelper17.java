package com.phonebook.tests.lesson17._02_helpers;

import org.openqa.selenium.By;

/*
 HomePageHelper17 — helper для главной страницы.
 Содержит проверки, относящиеся только к Home page.
*/

public class _2_4_HomePageHelper17 extends _2_1_BaseHelper17 {

    // Проверяет, что основной компонент главной страницы отображается.
    public boolean isHomeComponentPresent() {
        return isElementPresent(By.xpath("//div[2]//h1"));
    }
}