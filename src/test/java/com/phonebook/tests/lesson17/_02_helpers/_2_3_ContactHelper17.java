package com.phonebook.tests.lesson17._02_helpers;

import com.phonebook.tests.lesson17._03_model._3_2_Contact17;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
 ContactHelper17 — отвечает за работу с контактами:
 добавление, сохранение, удаление и проверки.

 Используется в тестах через ApplicationManager.
*/

public class _2_3_ContactHelper17 extends _2_1_BaseHelper17 {

    // Проверяет, появился ли контакт с указанным текстом (обычно имя).
    public boolean isContactCreatedByText(String text) {
        List<WebElement> contactsName = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contactsName) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает количество карточек контактов.
    protected int sizeOfContacts() {
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
        return 0;
    }

    // Нажатие кнопки Save на странице добавления контакта.
    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    // Заполняет форму добавления контакта данными из объекта Contact17 (VOM).
    public void fillContactForm(_3_2_Contact17 contact) {
        type(By.xpath("//input[1]"), contact.getName());
        type(By.xpath("//input[2]"), contact.getSurname());
        type(By.xpath("//input[3]"), contact.getPhoneNumber());
        type(By.xpath("//input[4]"), contact.getEmail());
        type(By.xpath("//input[5]"), contact.getAddress());
        type(By.xpath("//input[6]"), contact.getDescription());
    }

    // Переход на страницу добавления контакта.
    public void clickOnAddLink() {
        click(By.cssSelector("[href='/add']"));
    }

    // Удаляет первый контакт из списка.
    public void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
    }
}