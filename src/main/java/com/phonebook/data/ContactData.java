package com.phonebook.data;

/*
 ContactData
 Централизованные тестовые данные для создания контакта.
*/

public class ContactData {

    private ContactData() {
        // запрет создания объекта
    }

    public static final String name = "John";
    public static final String lastName = "Wick";
    public static final String phone = "1996664444";
    public static final String email = "john@gmail.com";
    public static final String address = "Berlin";
    public static final String description = "hitman";
}