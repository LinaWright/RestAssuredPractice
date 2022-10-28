package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class SpartanPostTest extends SpartanTestBase {
    /**
     * given accept type is json and content type is json
     * and body is
     * {
     *     "name": "JayChou",
     *     "gender": "Male",
     *     "phone": 8404524225
     * }
     * Then status code is 201
     * And content type is json
     * And "success" is "A Spartan is Born!"
     * Data name, gender, phone matches my request details
     */
    @DisplayName("POST new spartan using string json")
    @Test
    public void addNewSpartanAsJsonTest(){
        String jsonBody = "{\n" +
                "          \"name\": \"JayChou\",\n" +
                "          \"gender\": \"Male\",\n" +
                "          \"phone\": 8404524225\n" +
                "      }";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/spartans");
        response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        assertThat(response.statusCode(), equalTo(201));

        assertThat(response.contentType(),equalTo("application/json"));

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));
        assertThat(jsonPath.getString("data.name"), equalTo("JayChou"));
        assertThat(jsonPath.getString("data.gender"), equalTo("Male"));
        assertThat(jsonPath.getLong("data.phone"), equalTo(8404524225L));

        //extract the id of newly added spartan
        int spartanId = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanId);
        //delete the spartan after post
        SpartanRestUtils.deleteSpartanById(spartanId);

    }
    @DisplayName("POST /spartans using map - SERIALIZATION")
    @Test
    public void addNewSpartanAsMapTest(){
        /*
         *     "name": "JayChou",
         *     "gender": "Male",
         *     "phone": 8404524225
         */
        Map<String, Object> spartanPostMap = new HashMap<>();
        spartanPostMap.put("name","JayChou");
        spartanPostMap.put("gender","Male");
        spartanPostMap.put("phone","8404524225");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanPostMap) //Map to Json - serialization
                .when().post("/spartans");

        response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        assertThat(response.statusCode(), is(201));

        //verify header
        assertThat(response.contentType(), is("application/json"));

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"), equalTo("A Spartan is Born!"));

        assertThat(jsonPath.getString("data.name"), equalTo(spartanPostMap.get("name")));

        assertThat(jsonPath.getString("data.gender"), equalTo(spartanPostMap.get("gender")));
        assertThat(jsonPath.getLong("data.phone"), equalTo(spartanPostMap.get("phone")));

        //extract the id of newly added spartan
        int spartanId = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanId);
        //delete the spartan after post
        SpartanRestUtils.deleteSpartanById(spartanId);

    }
    @DisplayName("POST /spartans using POJO - SERIALIZATION")
    @Test
    public void addNewSpartanAsPOJOTest(){
        Spartan newSpartan = new Spartan();
        newSpartan.setGender("Male");
        newSpartan.setName("JayChou");
        newSpartan.setPhone(8404524225L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans");

    }

}