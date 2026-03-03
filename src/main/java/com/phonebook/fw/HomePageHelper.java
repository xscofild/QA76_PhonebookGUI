package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 HomePageHelper
 Логика, относящаяся к главной странице.
*/

public class HomePageHelper extends BaseHelper {

    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    // Проверка отображения основного заголовка Home страницы
    public boolean isHomeComponentPresent() {
        return isElementPresent(By.xpath("//div[2]//h1"));
    }

    // Переход на Home страницу через навигационную ссылку
    public void clickOnHomeLink() {
        click(By.cssSelector("[href='/home']"));
    }
}