package com.cydeo.tests.day12_jsonchema_authorization;

import com.cydeo.utils.SpartanSecureTestBase;
import com.cydeo.utils.SpartanRestUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpartanBasicAuthTest extends SpartanSecureTestBase {
    /**
     * given accept type is json
     * and basic auth credentials are admin, admin
     * when user sends GET request to /spartans
     * then status code is 200
     * And content type is json
     * print response
     */
    @DisplayName("GET /spartans with basic auth")
    @Test
    public void getSpartanWithAuthTest(){
        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().log().all();
    }

    /**
     * given accept type is json
     * when user sends GET request to /spartans
     * then status code is 401
     * And content type is json
     * print log response
     */

    @DisplayName("GET /spartans with negative auth")
    @Test
    public void getSpartanWithAuthNegativeTest(){
        given().accept(ContentType.JSON)
                .and().get("/spartans")
                .then().statusCode(401)
                .and().contentType(ContentType.JSON)
                .and().body("message",equalTo("Unauthorized"))
                .log().all();
    }
}
