package com.cydeo.tests.day02_headers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartaHeadersTest {
    String url = "http://3.93.242.50:8000/api/spartans";

    /**
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans data in JSON format
     */
    @Test
    public void getAllSpartansHeaderTest() {
        when().get(url)
                .then().assertThat().statusCode(200)
                //  .and().contentType("application/json");
                .and().contentType(ContentType.JSON);
    }
/**
 • Given Accept type is application/xml
 When I send a GET request to
 • spartan_base_url/api/spartans
 • Then Response STATUS CODE must be 200
 • And I Should see all Spartans data in JSON format
 */
    @Test
    public void acceptTypeHeaderTest(){
        given().accept(ContentType.XML)
                .when().get()
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML);
    }

    /**
     * Given Accept type is application/json
     • When I send a GET request to
     -----------------------------
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And read headers
     */
    @DisplayName("GET/api/spartans - read headers")
    @Test
    public void readResponseHeaders(){
        Response response = given().accept(ContentType.JSON)
                .when().get(url);
        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));
        System.out.println("Content type = " + response.getHeader("Content-type"));
        System.out.println("Connection = " + response.getHeader("Connection"));
        System.out.println("response.getHeaders() = " + response.getHeaders());

        //ensure header Keep_Alive is present

    }


}
