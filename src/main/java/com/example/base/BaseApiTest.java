package com.example.base;

import com.example.helpers.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseApiTest {

    protected static RequestSpecification requestSpec;
    public static final Logger logger = LoggerFactory.getLogger(BaseApiTest.class);

    static {
        String baseURI = ConfigLoader.getInstance().getProperty("api.baseURI");
        RestAssured.baseURI = baseURI;
        requestSpec = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all();
    }
}
