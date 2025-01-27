package com.example.steps;

import com.example.base.BaseApiTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class ApiSteps extends BaseApiTest {

    Response response;
    private static final Logger logger = LoggerFactory.getLogger(ApiSteps.class);

    public void apiLogs() {
        logger.info("Response Body: " + response.getBody().asString());
        logger.info("Content-Type: " + response.getContentType());
        logger.info("Status Code: " + response.statusCode());
    }

    public void getAllProducts() {
        response = requestSpec
                .when()
                .get("/productsList")
                .then()
                .statusCode(200)
                .body("products", notNullValue())
                .extract().response();
        apiLogs();
    }

    public void postAllProductsList() {
        response = given()
                .spec(requestSpec)
                .when()
                .post("/productsList");
        apiLogs();
        response.then()
                .statusCode(200)
                .body(containsString("405"))
                .body(containsString("This request method is not supported."));
    }

    public void getAllBrands() {
         response = requestSpec
                .when()
                .get("/brandsList")
                .then()
                .statusCode(200)
                .body("brands", notNullValue())
                .extract().response();
        apiLogs();
    }

    public void putAllBrands() {
        response = given()
                .spec(requestSpec)
                .when()
                .put("/brandsList");
        apiLogs();
        response.then()
                .statusCode(200)
                .body(containsString("405"))
                .body(containsString("This request method is not supported."));
    }

    public void searchProductNoParam() {
        response = given()
                .spec(requestSpec)
                .when()
                .post("/searchProduct");
        apiLogs();
        response.then()
                .statusCode(200)
                .body(containsString("400"))
                .body(containsString("Bad request, search_product parameter is missing in POST request."));
    }

    public void verifyLoginIncompleteParams() {
        response = given()
                .spec(requestSpec)
                .when()
                .delete("/verifyLogin");
        apiLogs();
        response.then()
                .statusCode(200)
                .body(containsString("405"))
                .body(containsString("This request method is not supported"));
    }

    public void getUserDetails() {
        response = given()
                .spec(requestSpec)
                .queryParam("email", "qIjEPhDRer@test.net")
                .when()
                .get("/getUserDetailByEmail");
        apiLogs();
        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 200"))
                .body(containsString("\"user\": {\"id\": 548292, \"name\": \"testUsername\", \"email\": \"qIjEPhDRer@test.net\", \"title\": \"Mr\", \"birth_day\": \"16\", \"birth_month\": \"8\", \"birth_year\": \"1997\", \"first_name\": \"John\", \"last_name\": \"Doe\", \"company\": \"Mirceasoft\", \"address1\": \"First address\", \"address2\": \"Second address\", \"country\": \"Canada\", \"state\": \"Ontario\", \"city\": \"Toronto\", \"zipcode\": \"123456\"}"));
    }
}
