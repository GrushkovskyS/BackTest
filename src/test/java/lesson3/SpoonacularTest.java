package lesson3;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
public class SpoonacularTest extends AbstractTest {

    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    @Test
    @Order(1)
    void newRequestTest(){
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl());
        System.out.println("Code" + response.getStatusCode());
    }

    @Test
    @Order(2)
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
                // .body("results[0].title", containsString("Pasta With Tuna"));
    }

    @Test
    @Order(13)
    void getResponseData(){
        String title = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .when()
                .get(getBaseUrl())
                .then().extract()
                .jsonPath()
                .get("results.title")
                .toString();
        if (title.contains("Pasta")) {
            System.out.println("title: " + title);
        }else{
            System.out.println("Нет совпадений");
        }
    }

    @Test
    @Order(3)
    void recipeInformationTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("addRecipeInformation",true)
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Order(4)
    void recipeNutritionTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("addRecipeNutrition",true)
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("results[0].title",equalTo("Pasta With Tuna"));
    }

    @Test
    @Order(5)
    void minProteinTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("minProtein",10)
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("results[0].title",equalTo("Pasta With Tuna"))
                .body("results[0].nutrition.nutrients[0].name",equalTo("Protein"));
    }

    @Test
    @Order(6)
    void maxProteinTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("maxProtein",100)
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("results[0].title",equalTo("Pasta With Tuna"))
                .body("results[0].nutrition.nutrients[0].name",equalTo("Protein"));
    }

    @Test
    @Order(7)
    void pastaMaxAllTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("minProtein",100)
                .queryParam("maxCalories",800)
                .queryParam("maxFat",100)
                .queryParam("maxCarbs",100)
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("results[0].title",equalTo("Pasta With Tuna"))
                .body("results[0].nutrition.nutrients[0].name",equalTo("Calories"))
                .body("results[0].nutrition.nutrients[1].name",equalTo("Protein"))
                .body("results[0].nutrition.nutrients[2].name",equalTo("Fat"))
                .body("results[0].nutrition.nutrients[3].name",equalTo("Carbohydrates"));
    }

    @Test
    @Order(8)
    void negativePastaMinProteinEmptyTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pasta")
                .queryParam("minProtein")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(404);
    }

    //Задание №2
    @Test
    @Order(9)
    void generateShoppingLisyTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getNewHash())
                .body("{\n"
                        + " \"username\": your-users-name6971,\n"
                        + " \"start-date\": 2023-02-17,\n"
                        + " \"end-date\": 2024-03-16,\n"
                        + " \"hash\": 5d9e932222ed15ba1d4f50651cb3826956b9c650\"\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name6971/shopping-list/2023-02-17/2024-03-16?" + "{hash}", getNewHash())
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    @Order(10)
    void addToShoppingList(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getNewHash())
                .body("{\n"
                        + " \"item\": \"1 package baking powder\",\n"
                        + " \"aisle\": \"Baking\",\n"
                        + " \"parse\": true\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/your-users-name6971/shopping-list/items?" + "{hash}", getNewHash())
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name",equalTo("baking powder"));
    }
    @Test
    @Order(11)
    void getShoppingListTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getNewHash())
                .when()
                .get("https://api.spoonacular.com/mealplanner/your-users-name6971/shopping-list?" + "{hash}", getNewHash())
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    @Order(12)
    void DelFromShoppingListTest(){
      String id = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getNewHash())
                .when()
                .get("https://api.spoonacular.com/mealplanner/your-users-name6971/shopping-list")
                .then()
                .extract()
                .jsonPath()
                .get("aisles[0].items[0].id")
                .toString();

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getNewHash())
                .when()
                .delete("https://api.spoonacular.com/mealplanner/your-users-name6971/shopping-list/items/"+ id)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status",equalTo("success"));
    }
}
