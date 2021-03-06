import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

//первый тест на rest-assuared
public class TestRest {
    //логин и пароль для авторизации
    static String username = "";
    static String password = "";
    //по тестнг перед тестом настройка, в нашем случае комплекс и способ авторизации при отправке запроса
    @BeforeTest
    public static void setup()
    {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme(); // создание схемы для авторизации
        authScheme.setUserName(username); //логин
        authScheme.setPassword(password); //пароль

        authentication = authScheme; // в общем это кусок гавно-кода мутанта из https://github.com/rest-assured/rest-assured/wiki/Usage#authentication и какого-то вопроса на стековерфлоу
        baseURI = "http://api.client-service.bus.stage2.auto3n.ru"; //базовая урла, в данном случае стейдж
        basePath = "/v2/entity/AUTO3N/PersonProfile/"; //для пп свой кусок, как вариант можно потом наклепать еще для других сущностей
    }

    @Test(description = "GET") //по тестнг объявление тест-кейса(в виде анотации) и дескрипт (Условно проверка на существование объекта и вывод объекта)
    public void getRequest_1() throws JSONException {
        given() // состояние, до того, как начнется описание поведения. можно рассматривать как предварительное условие теста.-ДАНО
                .auth()//авторизация
                .preemptive()//указываем что используем базу из предтеста
                .basic(username,password)//данные для входа
                .contentType("application/json")
        .when() // поведение которое эмулируем, в данной ситуации мы прочить получить гет от урла=базовый+кусок+path
                .get("/242")
        .then() // ожидаемый результат, если его нет, то выведется че оказалось на самом деле
                .statusCode(200);
        /*И все это GIVEN-WHEN-THEN называется "Спецификация поведения системы" и все это оказалось BDD - разработка через поведение. Учение свет не ученье тьма*/
        System.out.println(get("/242").asString());
    }

    @Test(description = "GET")//тут хотелось бы так же получить json его распарсить и посмотреть имя пользователя. жесткий вопрос как и чем парсить, потому что с org.json там надо будет по всему ходить что не не круто...
    public void getRequest_2() throws JSONException{
        given()
                .auth()
                .preemptive()
                .basic(username,password)
                .contentType("application/json")
        .when()
                .get("/242")
        .then()
                .statusCode(200);
        System.out.println(get("/242").asString());
        JSONObject jR = new JSONObject(get("/242").asString());
        System.out.println(jR);
        System.out.println(jR.get("PersonProfile"));
        //String str = jR.getString("PersonProfile.name");
        //Assert.assertEquals(str, "Бабин Александр Павлович");
    }
}
