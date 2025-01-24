package com.example.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {

    protected static RequestSpecification requestSpec;

    static {
        RestAssured.baseURI = "https://automationexercise.com/api";
        requestSpec = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all();
    }
}
