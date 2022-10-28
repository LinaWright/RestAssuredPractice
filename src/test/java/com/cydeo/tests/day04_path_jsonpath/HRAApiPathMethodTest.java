package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HRAApiPathMethodTest extends HRApiTestBase {
    Response response = given().accept(ContentType.JSON)
            .when().get("countries");


        List<String>countryNames = response.path("items.country_name");

}
