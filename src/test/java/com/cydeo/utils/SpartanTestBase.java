package com.cydeo.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public abstract class SpartanTestBase {
    protected static RequestSpecification requestSpecification;
    @BeforeAll //@BeforeClass in junit 4
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.api.url");
        requestSpecification = given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin");
    }
}
