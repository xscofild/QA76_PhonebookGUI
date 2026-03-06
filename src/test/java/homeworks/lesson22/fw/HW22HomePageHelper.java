package homeworks.lesson22.fw;

import homeworks.lesson22.core.HW22BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 HW22HomePageHelper — действия на главной странице приложения.
*/
public class HW22HomePageHelper extends HW22BaseHelper {

    public HW22HomePageHelper(WebDriver driver) {
        super(driver);
    }

    // Проверяет что главная страница загружена — ищет заголовок h1 в нужном div.
    // Используется в @BeforeMethod HW22HomePageTests для проверки состояния перед тестом.
    public boolean isHomeComponentPresent() {
        return isElementPresent(By.xpath("//div[2]//h1"));
    }

    public void clickOnHomeLink() {
        click(By.cssSelector("[href='/home']"));
    }
}
