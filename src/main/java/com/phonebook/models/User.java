package com.phonebook.models;

/*
 User — модель данных пользователя (Value Object).

 Передаётся в UserHelper.fillLoginRegisterForm().

 Fluent-style (цепочка вызовов) — удобно создавать объект в одну строку:
   new User().setEmail("test@mail.com").setPassword("Pass123!");

 Не содержит логики — только хранение данных.
*/
public class User {

    private String email;
    private String password;

    // Возвращает this — позволяет цепочку: new User().setEmail(...).setPassword(...)
    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail()    { return email; }
    public String getPassword() { return password; }
}
