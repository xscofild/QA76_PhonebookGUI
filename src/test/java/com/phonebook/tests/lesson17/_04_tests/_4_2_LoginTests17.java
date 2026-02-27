package com.phonebook.tests.lesson17._04_tests;

import com.phonebook.tests.lesson17._01_manager._1_2_TestBase17;
import com.phonebook.tests.lesson17._03_model._3_1_User17;
import com.phonebook.tests.lesson17._03_model._3_3_UserData17;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 LoginTests17 — тесты авторизации пользователя.

 Проверяют:
 - успешный вход с валидными данными
 - ошибку при отсутствии email
*/

public class _4_2_LoginTests17 extends _1_2_TestBase17 {

    @Test
    public void loginPositiveTest() {

        // Авторизация с корректными данными
        app17.clickOnLoginLink();
        app17.fillLoginRegisterForm(new _3_1_User17()
                .setEmail(_3_3_UserData17.email)
                .setPassword(_3_3_UserData17.password));
        app17.clickOnLoginButton();

        // После успешного входа должна появиться кнопка Sign Out
        Assert.assertTrue(app17.isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeWithoutEmailTest() {

        // Попытка входа без указания email
        app17.clickOnLoginLink();
        app17.fillLoginRegisterForm(new _3_1_User17()
                .setPassword(_3_3_UserData17.password));
        app17.clickOnLoginButton();

        // Ожидаем сообщение об ошибке (alert)
        Assert.assertTrue(app17.isAlertPresent());
    }
}