package homeworks.lesson22.data;

/*
 HW22ContactData — тестовые данные контакта по умолчанию.

 Используется в позитивных тестах создания и удаления контакта.
 Поля static final — константы, создавать объект не нужно: HW22ContactData.name

 Для наборов данных (несколько контактов) — используй ContactData.csv
 или @DataProvider в HW22AddContactTests.
*/
public class HW22ContactData {

    private HW22ContactData() {} // запрещаем создание экземпляра — утилитный класс

    public static final String name        = "Serdar";
    public static final String lastName    = "Kerimov";
    public static final String phone       = "4915123456789";
    public static final String email       = "serdar@gmail.com";
    public static final String address     = "Berlin";
    public static final String description = "QA Engineer";
}
