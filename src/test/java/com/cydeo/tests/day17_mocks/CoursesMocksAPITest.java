package com.cydeo.tests.day17_mocks;

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

public class CoursesMocksAPITest {
    @BeforeAll
    public static void setUp() {
        baseURI = "https://e0496f3a-5943-4f69-83fe-d79ff24acbec.mock.pstmn.io";
    }


    /**
     * Given accept type is json
     * When I send get request to /courses
     * Then status code is 200
     * And content type is json
     * And body courseIds contain 1,2,3
     * And courseNames are "Java SDET", "Java Developer", "Cyber Security Analyst"
     */
    @DisplayName("GET /courses mock api")
    @Test
    public void courseMockTest() {
        given().accept(ContentType.JSON)
                .when().get("/courses")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("courseId",hasItems(1,2,3),
                        "courseName",hasItems("Java SDET", "Java Developer", "Cyber Security Analyst"))
                .log().all();
    }
}
