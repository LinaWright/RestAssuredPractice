package com.cydeo.tests.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecResAPITest {
    /**
     * When User sends GET Request to
     * https://reqres.in/api/users
     * <p>
     * Then Response status code should be 200
     * And Response body should contain "George"
     * And Header Content type should be json
     */

    String url = "https://reqres.in/api/users";

    @DisplayName("GET all users")
    @Test
    public void usersGetTest() {
        //when is BDD "Gerkin" just make the code more readable
        Response response = when().get(url);
        //RestAssured.get(url); both are the same, the one above is more readable

        //Then Response status code should be 200
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        //BDD syntax
        response.then().statusCode(200);
        response.then().assertThat().statusCode(200);

        //And Response body should contain "George"
        System.out.println("Response jason body = " + response.prettyPrint());
        assertTrue(response.asString().contains("George"));

        //And Header Content type should be json
        System.out.println("Content type header value = " + response.contentType());
        assertTrue(response.contentType().contains("application/json"));

    }

    /**
     * When User Sends get request to API Endpoint:
     * "https://reqres.in/api/users/5"
     * Then Response status code should be 200
     * And Response body should contain user info "Charles"
     */
    @DisplayName("GET single user")
    @Test
    public void getSingleUserApiTest() {
        Response response = when().get(url + "/5");

        System.out.println("status code = " + response.statusCode());

        //* Then Response status code should be 200
        assertEquals(200, response.getStatusCode());

        //* And Response body should contain user info "Charles"
        response.prettyPrint();
        assertTrue(response.asString().contains("Charles"));

    }

    @DisplayName("GET request to non existing user")
    @Test
    public void getSingleUserNegativeApiTest() {
        Response response = when().get(url + "/50");

        System.out.println("Status code = " + response.statusCode());
        assertEquals(404, response.statusCode());

    }
}
