import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
//первый тест на rest-assuared
public class TestRest {
    //логин и пароль для авторизации
    static String username = "ERP";
    static String password = "erp2016auto3n-stage";
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

    @Test(description = "GET") //по тестнг объявление тест-кейса(в виде анотации) и дескрипт
    public void getRequest() throws JSONException {
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
    }

}
