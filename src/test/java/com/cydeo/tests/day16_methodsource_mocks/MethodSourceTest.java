package com.cydeo.tests.day16_methodsource_mocks;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * data provider method
 */
public class MethodSourceTest {
    public static List<String> getCountries(){
        List<String>countries = Arrays.asList("Canada","USA","France","Turkey","Mexico","China");

        return countries;
    }
    @ParameterizedTest
    @MethodSource("getCountries")
    public void countriesTest(String countryName){
        System.out.println("countryName="+countryName);
        System.out.println(countryName.toUpperCase());
    }
}
