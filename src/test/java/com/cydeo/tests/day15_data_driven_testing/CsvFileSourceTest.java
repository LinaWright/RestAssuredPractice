package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class CsvFileSourceTest {
    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @CsvFileSource (resources = "/ZipCodes.csv", numLinesToSkip = 1)
    public void zipCodeTest(String state, String city, int zipCount){
        Map<String,String>paramsMap = new HashMap<>();
        paramsMap.put("state",state);
        paramsMap.put("city",city);
        given().accept(ContentType.JSON)
                .and().pathParams(paramsMap)
                .when().get("us/{state}/{city}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("places",hasSize(zipCount))
                .log().all();
    }
}
