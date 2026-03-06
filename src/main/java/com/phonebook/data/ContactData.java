package com.phonebook.data;

/*
 ContactData — тестовые данные контакта по умолчанию.

 Используется в позитивных тестах создания и удаления контакта.
 Поля static final — константы, создавать объект не нужно: ContactData.name

 Для наборов данных (несколько контактов) — используй ContactData.csv
 или @DataProvider в AddContactTests.
*/
public class ContactData {

    private ContactData() {} // запрещаем создание экземпляра

    public static final String name        = "John";
    public static final String lastName    = "Wick";
    public static final String phone       = "1996664444";
    public static final String email       = "john@gmail.com";
    public static final String address     = "Berlin";
    public static final String description = "hitman";
}
