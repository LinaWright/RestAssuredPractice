package com.cydeo.tests.day07_deserialization;
import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.SpartanSearch;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanSearchPOJOTest extends SpartanTestBase{
    @Test
    public void spartanSearchToPOJOTest(){

          /**
         *      Given accept type is json
         *      And query param nameContains value is "e"
         *      And query param gender value is "Female"
         *      When I send get request to /spartans/search
         *      Then status code is 200
         *      And content type is Json
         *      And json response data is as expected
         *      */

        Map<String,String>queryMap=new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/spartans/search");
        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //deserialize json to SpartanSearch pojo class
        SpartanSearch spartanSearch = response.body().as(SpartanSearch.class);

        //total elements
        System.out.println("TotalElement = " + spartanSearch.getTotalElement());
        System.out.println("allSpartans = " + spartanSearch.getContent());
        System.out.println("first Spartan info = " + spartanSearch.getContent().get(0));

        //go to content list of spartans and get index 1 single spartan
        Spartan secondSpartan = spartanSearch.getContent().get(1);
        System.out.println("secondSpartan = " + secondSpartan);
        System.out.println("second Spartan name = " + secondSpartan.getName());
        System.out.println("second Spartan id = " + secondSpartan.getId());
        System.out.println("second Spartan name = " + secondSpartan.getName());

        List<Spartan> spartanData = spartanSearch.getContent();
        System.out.println("second spartan = " + spartanData.get(1));

        //read all names into a list
        List<String>names = new ArrayList<>();
        for (Spartan sp :spartanData){
            names.add(sp.getName());
        }

        System.out.println("names = " + names);

        //Using
        List<String> allNames = spartanData.stream().map(spartan -> spartan.getName()).collect(Collectors.toList());
        System.out.println("allNames = " + allNames);
    }
}
