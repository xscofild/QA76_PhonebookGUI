package homeworks.lesson22.data;

/*
 HW22UserData — тестовые данные существующего пользователя.

 Используется во всех тестах где нужна авторизация.
 Поля static final — константы, создавать объект не нужно: HW22UserData.email

 ⚠ В реальных проектах credentials выносят в .env / CI secrets,
   а не хранят в коде. Здесь учебный проект — поэтому хардкод допустим.
*/
public class HW22UserData {

    private HW22UserData() {} // запрещаем создание экземпляра — утилитный класс

    public static final String email    = "serdarkerimov@gmail.com";
    public static final String password = "Qwertz123!";
}
