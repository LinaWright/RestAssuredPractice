package com.cydeo.tests.day16_methodsource_mocks;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MockStudentApiTest {
    @BeforeAll
    public static void setUp(){
        baseURI = "https://e0496f3a-5943-4f69-83fe-d79ff24acbec.mock.pstmn.io";
    }

    @DisplayName("GET /student/1")
    @Test
    public void testStudent(){
        given().accept(ContentType.JSON)
                .when().get("students/1")
                .then().assertThat().statusCode(200)
                .log().all();
    }
}
