package com.example.base;

import com.example.helpers.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {

    protected static RequestSpecification requestSpec;

    static {
        String baseURI = ConfigLoader.getInstance().getProperty("api.baseURI");
        RestAssured.baseURI = baseURI;
        requestSpec = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all();
    }
}
