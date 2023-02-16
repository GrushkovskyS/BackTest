package lesson3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoggingTest extends AbstractTest {

    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void pastaTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("results[0].title",equalTo("Pasta With Tuna"));
    }

}
