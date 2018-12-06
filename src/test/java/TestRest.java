import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.json.JSONException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestRest {

    static String username = "ERP";
    static String password = "erp2016auto3n-stage";

    @BeforeTest
    public static void setup()
    {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(username);
        authScheme.setPassword(password);

        authentication = authScheme;
        baseURI = "http://api.client-service.bus.stage2.auto3n.ru";
        basePath = "/v2/entity/AUTO3N/PersonProfile/";
    }

    @Test(description = "GET")
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
