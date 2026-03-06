package com.phonebook.data;

// Данные существующего пользователя для тестов авторизации.
// ⚠ В реальных проектах — выносить в .env / CI secrets.
public class UserData {

    private UserData() {}

    public static final String email    = "serdarkerimov@gmail.com";
    public static final String password = "Qwertz123!";
}
