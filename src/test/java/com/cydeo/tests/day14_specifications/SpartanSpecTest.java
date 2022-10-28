package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cydeo.utils.SpartanTestBase;

import javax.print.attribute.standard.RequestingUserName;

public class SpartanSpecTest extends SpartanTestBase {
//    RequestSpecification requestSpecification = given().accept(ContentType.JSON)
//            .and().auth().basic("admin","admin");
//    ResponseSpecification responseSpecification = expect().statusCode(200)
//        .and().contentType(ContentType.JSON);
    @Test
    public void allSpartansTest(){
        given().spec(requestSpecification)
                .when().get("/spartans")
                .then().spec(responseSpecification)
               .log().all();
    }

    @Test
    public void singleSpartansTest(){
        given().spec(requestSpecification)
                .and().pathParam("id",15)
                .when().get("/spartans/{id}")
                .then().spec(responseSpecification)
                .log().all();
    }

}
