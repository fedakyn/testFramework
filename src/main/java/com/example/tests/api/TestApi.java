package com.example.tests.api;

import com.example.base.BaseApiTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

public class TestApi extends BaseApiTest {

    @Test
    public void getAllProducts(){
        Response response = requestSpec
                .when()
                .get("/productsList")
                .then()
                .statusCode(200)
                .body("products", notNullValue())
                .extract().response();
        System.out.println("Response: " + response.getBody().asString());
    }

    @Test
    public void postAllProductsList(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .post("/productsList");

        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Content-Type: " + response.getContentType());
        System.out.println("Status Code: " + response.statusCode());

        response.then()
                .statusCode(200)
                .body(containsString("405"))
                .body(containsString("This request method is not supported."));
    }

    @Test
    public void getAllBrands(){
        Response response = requestSpec
                .when()
                .get("/brandsList")
                .then()
                .statusCode(200)
                .body("brands", notNullValue())
                .extract().response();
        System.out.println("Response: " + response.getBody().asString());
    }

    @Test
    public void putAllBrands(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .put("/brandsList");

        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Content-Type: " + response.getContentType());
        System.out.println("Status Code: " + response.statusCode());

        response.then()
                .statusCode(200)
                .body(containsString("405"))
                .body(containsString("This request method is not supported."));
    }

    @Test
    public void SearchProductNoParam(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .post("/searchProduct");

        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Content-Type: " + response.getContentType());
        System.out.println("Status Code: " + response.statusCode());

        response.then()
                .statusCode(200)
                .body(containsString("400"))
                .body(containsString("Bad request, search_product parameter is missing in POST request."));
    }

    @Test
    public void VerifyLoginIncompleteParams(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .delete("/verifyLogin");

        response.then()
                .statusCode(200)
                .body(containsString("405"))
                .body(containsString("This request method is not supported"));
    }

//    @Test //api not working properly
//    public void CreateUserAccount(){
//        String randomEmail = Util.generateRandomString(10);
//        Response response = given()
//                .spec(requestSpec)
//                .queryParam("name", "john")
//                .queryParam("email", randomEmail + "@test.net")
//                .queryParam("password", "testPassword")
//                .queryParam("title","Mr")
//                .queryParam("birth_date", "6")
//                .queryParam("birth_month", "August")
//                .queryParam("birth_year", "1992")
//                .queryParam("firstname", "john")
//                .queryParam("lastname","doe")
//                .queryParam("company","apple")
//                .queryParam("address1","street")
//                .queryParam("address2","block")
//                .queryParam("country", "India")
//                .queryParam("zipcode", "325001")
//                .queryParam("state", "Romania")
//                .queryParam("city","Iasi")
//                .queryParam("mobile_number","1234567889")
//                .when()
//                .post("/createAccount");
//
//        response.then()
//                .statusCode(201)
//                .body(containsString("User Created"));
//    }

    @Test
    public void GetUserDetails(){
        Response response = given()
                .spec(requestSpec)
                .queryParam("email","qIjEPhDRer@test.net")
                .when()
                .get("/getUserDetailByEmail");

        System.out.println("Response Body: " + response.getBody().asString());

        response.then()
                .statusCode(200)
                .body(containsString("\"responseCode\": 200"))
                .body(containsString("\"user\": {\"id\": 548292, \"name\": \"testUsername\", \"email\": \"qIjEPhDRer@test.net\", \"title\": \"Mr\", \"birth_day\": \"16\", \"birth_month\": \"8\", \"birth_year\": \"1997\", \"first_name\": \"John\", \"last_name\": \"Doe\", \"company\": \"Mirceasoft\", \"address1\": \"First address\", \"address2\": \"Second address\", \"country\": \"Canada\", \"state\": \"Ontario\", \"city\": \"Toronto\", \"zipcode\": \"123456\"}"));
    }
}
