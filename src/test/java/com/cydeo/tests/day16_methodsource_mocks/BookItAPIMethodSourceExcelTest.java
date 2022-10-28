package com.cydeo.tests.day16_methodsource_mocks;

import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ExcelUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookItAPIMethodSourceExcelTest extends BookItAPITestBase {
    @DisplayName("GET /sign -> data from BookItQa3.xlsx")
    @ParameterizedTest
    @MethodSource("getUserCredentials")
    public void bookOtAuthDDTTest(Map<String,String> UserInfo){
        System.out.println("UserInfo = " + UserInfo);//can be used as QueryParams in API call
        //API call/request to /sign and verify status code and access code is there
        given().accept(ContentType.JSON)
                .and().queryParams(UserInfo)
                .when().get("/sign")
                .then().spec(responseSpec)//status code, contentType
                .and().body("accessToken",not(blankOrNullString()));
    }

    public static List<Map<String,String>> getUserCredentials(){
        String filePath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath,"QA3");
        List<Map<String,String>>data = excelUtil.getDataList();
        return data;
    }

    @DisplayName("GET /sign -> using a for loop")
    @Test
    public void loginTestUsingLoop(){
        List<Map<String,String>>allCredentials = getUserCredentials();


      for (Map<String, String> userInfo: allCredentials) {
        System.out.println("userInfo = " + userInfo);//can be used as QueryParams in API call.
        //API call/request to /sign and verify status code and access code is there
        try {
            given().accept(ContentType.JSON)
                    .and().queryParams(userInfo)
                    .when().get("/sign")
                    .then().spec(responseSpec)//status code, contentType
                    .and().body("accessToken", not(blankOrNullString()));
        }catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}
}