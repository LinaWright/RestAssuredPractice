package com.cydeo.tests.day13_access_token;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class BookItAccessTokenTest {
    /**
     * Given accept type is Json
     * And Query params: email = blyst6@si.edu & password = barbabaslyst
     * When I send GET request to
     * Url: https://cybertek-reservation-api-qa3.herokuapp.com/sign
     * Then status code is 200
     * And accessCode should be present
     * And accessToken should be present and not empty
     */
    @DisplayName("GET /sign - request with access token")
    @Test
    public void bookItLoginTest(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("email", "blyst6@si.edu")
                .and().queryParams("password", "barbabaslyst")
                .when().get("https://cybertek-reservation-api-qa3.herokuapp.com/sign");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());

        Assertions.assertEquals(200,response.statusCode());

        String accessToken = response.path("accessToken");
        System.out.println(accessToken);
        //assert accessToken is not empty
        Assertions.assertTrue(accessToken!=null && !accessToken.isEmpty());

    }
}
