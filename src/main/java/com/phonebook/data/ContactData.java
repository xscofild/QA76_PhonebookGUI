package com.phonebook.data;

/*
 ContactData — тестовые данные для контакта.

 Используется в тестах создания и проверки контакта.
 Все поля статические — создавать объект не нужно.
*/
public class ContactData {

    private ContactData() {}

    public static final String name        = "John";
    public static final String lastName    = "Wick";
    public static final String phone       = "1996664444";
    public static final String email       = "john@gmail.com";
    public static final String address     = "Berlin";
    public static final String description = "hitman";
}