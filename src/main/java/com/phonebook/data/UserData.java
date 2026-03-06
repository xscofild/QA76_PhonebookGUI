package com.phonebook.data;

/*
 UserData — тестовые данные существующего пользователя.

 Используется во всех тестах где нужна авторизация.
 Поля static final — константы, создавать объект не нужно: UserData.email

 ⚠ В реальных проектах credentials выносят в .env / CI secrets,
   а не хранят в коде. Здесь учебный проект — поэтому хардкод допустим.
*/
public class UserData {

    private UserData() {} // запрещаем создание экземпляра

    public static final String email    = "serdarkerimov@gmail.com";
    public static final String password = "Qwertz123!";
}
