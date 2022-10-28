package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JUnitValueSourceTest {
    @ParameterizedTest
    @ValueSource(ints = {5, 23, 90, 33, 64, 10986, 456})
    public void numbersTest(int num) {
        System.out.println("num = " + num);
        assertThat(num, is(greaterThan(0)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Lina", "David", "Elijah", "Noah"})
    public void testNames(String name) {
        System.out.println("I love you " + name);
        assertThat(name,not(blankOrNullString()));
    }

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }
    @ParameterizedTest
    @ValueSource(ints = {22102,22031,22034,22316,22698,22346})
    public void zipCodeTest(int zipCode){
        given().accept(ContentType.JSON)
                .and().pathParam("postal-code",zipCode)
                .when().get("us/{postal-code}")
                .then().assertThat().statusCode(200)
                .log().all();
    }
}
