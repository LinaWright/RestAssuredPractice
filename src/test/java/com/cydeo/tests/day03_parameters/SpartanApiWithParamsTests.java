package com.cydeo.tests.day03_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiWithParamsTests {
    String url = "http://3.93.242.50:8000/api/spartans";

    @DisplayName("Get/api/spartans/{id}")
    @Test

    public void getSingleSpartan() {
        /**
         Given accept type is Json
         And Id parameter value is 5
         When user sends GET request to /api/spartans/{id}
         Then response status code should be 200
         And response content-type: application/json
         And "Blythe" should be in response payload(body)
         */
//        given().accept(ContentType.JSON)
//                .when().get(url+"/5");

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get(url + "/{id}");

        response.prettyPrint();
        System.out.println("Status code = " + response.statusCode());
        //assertEquals(200, response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.contentType() = " + response.getHeader("content-type"));

        assertEquals("application/json", response.contentType());
        assertTrue(response.asString().contains("Blythe"));
    }

    /**
     * Given accept type is Json
     * And Id parameter value is 500
     * When user sends GET request to /api/spartans/{id}
     * Then response status code should be 404
     * And response content-type: application/json
     * And "Not Found" message should be in response payload
     */
    @DisplayName("Get/api/spartans/{id} with missing id")
    @Test
    public void getSingleSpartanBotFound() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(url + "/{id}");
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        response.prettyPrint();
        assertTrue(response.asString().contains("Not Found"));
    }


}
