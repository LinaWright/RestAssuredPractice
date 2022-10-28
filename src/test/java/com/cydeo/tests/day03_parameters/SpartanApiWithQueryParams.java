package com.cydeo.tests.day03_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiWithQueryParams {
    /*
        Given Accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    String url = "http://3.93.242.50:8000/api/spartans/search";

    @DisplayName(("Get /api/spartans/search with query/spartans"))
    @Test
    public void searchGorSpartanTest() {
        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("gender", "female")
                .and().queryParam("nameContains", "e")
                .when().get(url);

        //verify status code
        assertEquals(200,response.statusCode());

        //verify response header
        assertEquals("application/json",response.contentType());

        //And "Female" should be in response payload
        //And "Janette" should be in response payload
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));
    }
}
