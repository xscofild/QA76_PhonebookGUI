package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 HomePageHelper — действия на главной странице приложения.
*/
public class HomePageHelper extends BaseHelper {

    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    // Проверяет что главная страница загружена — ищет заголовок h1 в нужном div.
    // Используется в @BeforeMethod HomePageTests для проверки состояния перед тестом.
    public boolean isHomeComponentPresent() {
        return isElementPresent(By.xpath("//div[2]//h1"));
    }

    public void clickOnHomeLink() {
        click(By.cssSelector("[href='/home']"));
    }
}
