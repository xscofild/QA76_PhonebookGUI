package homeworks.lesson22.models;

/*
 HW22User — модель данных пользователя (Value Object).

 Передаётся в HW22UserHelper.fillLoginRegisterForm().

 Fluent-style (цепочка вызовов) — удобно создавать объект в одну строку:
   new HW22User().setEmail("serdarkerimov@gmail.com").setPassword("Qwertz123!");

 Не содержит логики — только хранение данных.
*/
public class HW22User {

    private String email;
    private String password;

    // Возвращает this — позволяет цепочку: new HW22User().setEmail(...).setPassword(...)
    public HW22User setEmail(String email) {
        this.email = email;
        return this;
    }

    public HW22User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail()    { return email; }
    public String getPassword() { return password; }
}
