package com.demo.prep.tests;

import com.demo.prep.singleton.ConfigManager;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.Assert.assertNotNull;

public class RestAssuredApiTest {

    @Test
    public void getUsersReturnsNonEmptyCollection() {
        String baseUrl = ConfigManager.getInstance().getApiBaseUrl();

        given()
                .when().get(baseUrl + "/users")
                .then().statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void createTodoReturnsCreatedResourceId() {
        String baseUrl = ConfigManager.getInstance().getApiBaseUrl();

        String id = given()
                .contentType(ContentType.JSON)
                .body("{\"title\":\"prep-demo\",\"completed\":false,\"userId\":1}")
                .when().post(baseUrl + "/todos")
                .then().statusCode(201)
                .extract().path("id").toString();

        assertNotNull(id);
    }
}