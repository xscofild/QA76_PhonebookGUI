package homeworks.lesson22.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

/*
 HW22BaseHelper — базовый слой для всех helper-ов.

 Содержит только универсальные методы работы с UI:
   - поиск элементов
   - клики и ввод текста
   - ожидания (явные и неявные)
   - скриншоты

 Все конкретные helper-ы (HW22UserHelper, HW22ContactHelper, HW22HomePageHelper)
 наследуют этот класс и получают эти методы бесплатно.
 Логики конкретных страниц здесь нет.
*/
public class HW22BaseHelper {

    protected WebDriver driver;

    public HW22BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Возвращает true если хотя бы один элемент найден.
    // Не бросает исключение при отсутствии элемента — в отличие от findElement().
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // Клик перед sendKeys нужен чтобы сфокусироваться на поле.
    // text == null → поле пропускается (поддержка необязательных полей формы).
    // clear() — очищает поле перед вводом, на случай если там уже что-то есть.
    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Ждёт alert до 5 сек, принимает (accept) и возвращает true.
    // Если alert не появился — возвращает false (не бросает исключение).
    // Используется там, где alert появляется не всегда (негативные тесты).
    public boolean isAlertPresent() {
        try {
            Alert alert = getWait(5)
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Фабричный метод для WebDriverWait с заданным таймаутом в секундах.
    // Используй в helper-ах вместо Thread.sleep() там, где есть чёткое условие.
    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // ⚠ Антипаттерн — не использовать без крайней необходимости!
    // Только если нет явного условия для ожидания (например, анимация без события).
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Делает скриншот экрана браузера и сохраняет в папку screenshots/.
    // Вызывается автоматически из HW22TestBase.stopTest() при падении теста.
    // Если есть alert — сначала закрывает его, иначе скриншот не получится.
    public String takeScreenshot() {

        // Закрываем alert если он открыт — иначе TakesScreenshot упадёт.
        try {
            Alert alert = getWait(3).until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception ignored) {
        }

        // TakesScreenshot — интерфейс Selenium, ChromeDriver/FirefoxDriver его реализует.
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Уникальное имя файла через timestamp — чтобы не перезаписывать предыдущие.
        File screenshot = new File("screenshots/hw22-screen-" + System.currentTimeMillis() + ".png");

        // Создаём папку screenshots/ если её нет (актуально при запуске через Jenkins).
        screenshot.getParentFile().mkdirs();

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return screenshot.getAbsolutePath();
    }
}
