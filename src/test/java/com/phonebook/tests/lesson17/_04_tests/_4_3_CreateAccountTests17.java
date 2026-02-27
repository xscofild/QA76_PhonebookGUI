package com.phonebook.tests.lesson17._04_tests;

import com.phonebook.tests.lesson17._01_manager._1_2_TestBase17;
import com.phonebook.tests.lesson17._03_model._3_1_User17;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 CreateAccountTests17 — тесты регистрации пользователя.

 Проверяют:
 - успешную регистрацию нового пользователя
 - ошибку при регистрации уже существующего пользователя
*/

public class _4_3_CreateAccountTests17 extends _1_2_TestBase17 {

    @Test
    public void newUserRegisterPositiveTest() {

        // Регистрация пользователя
        app17.clickOnLoginLink();
        app17.fillLoginRegisterForm(new _3_1_User17()
                .setEmail("serdarkerimov@gmail.com")
                .setPassword("Qwertz123!"));
        app17.clickOnRegistrationButton();

        // После успешной регистрации должна появиться кнопка Sign Out
        Assert.assertTrue(app17.isSignOutButtonPresent());
    }

    @Test
    public void existedUserRegisterNegativeTest() {

        // Попытка зарегистрировать уже существующего пользователя
        app17.clickOnLoginLink();
        app17.fillLoginRegisterForm(new _3_1_User17()
                .setEmail("serdarkerimov@gmail.com")
                .setPassword("Qwertz123!"));
        app17.clickOnRegistrationButton();

        // Ожидаем появление alert
        Assert.assertTrue(app17.isAlertPresent());
    }
}