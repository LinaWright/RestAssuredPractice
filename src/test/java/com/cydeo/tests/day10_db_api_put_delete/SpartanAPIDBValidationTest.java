package com.cydeo.tests.day10_db_api_put_delete;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.DB_Utils;
import com.cydeo.utils.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class SpartanAPIDBValidationTest extends SpartanTestBase {
    /**
     * given accept is json and content type is json
     * and body is:
     * {
     * "gender": "Male",
     * "name": "TestPost1"
     * "phone": 1234567425
     * }
     * When I send POST request to /spartans
     * Then status code is 201
     * And content type is json
     * And "success" is "A Spartan is Born!"
     * When I send database query
     * SELECT name, gender, phone
     * FROM spartans
     * WHERE spartan_id = newIdFrom Post request;
     * Then name, gender , phone values must match with POST request details
     */
    @DisplayName("POST /api/spartan -> then validate in DB")
    @Test
    public void postNewSpartanThenValidateInDBTest() {
        Map<String, Object> postRequestMap = new HashMap<>();
        postRequestMap.put("gender","Male");
        postRequestMap.put("name","TestPost1");
        postRequestMap.put("phone","1234567425");

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap) //set request json body map -> json
                .when().post("/spartans");
        JsonPath jsonPath = response.jsonPath();

        response.prettyPrint();

        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.contentType(),equalTo("application/json"));
        assertThat(jsonPath.getString("success"),equalTo("A Spartan is Born!"));

        int newSpartanID = jsonPath.getInt("data.id");
        System.out.println("newSpartanID = " + newSpartanID);

        //database steps
        String query = "select SPARTAN_ID, NAME,GENDER,PHONE\n" +
                "from SPARTANS\n" +
                "where SPARTAN_ID = " + newSpartanID;

        String dbUrl = ConfigurationReader.getProperty("spartan.db.url");
        String dbUserName = ConfigurationReader.getProperty("spartan.db.userName");
        String dbPassword = ConfigurationReader.getProperty("spartan.db.password");

        //Connect to DB
        DB_Utils.createConnection(dbUrl,dbUserName,dbPassword);

        //run the query and get result as Map object
        Map<String, Object> dbMap = DB_Utils.getRowMap(query);

        //assert/validate data from database Matches data from post request
        assertThat(dbMap.get("GENDER"),equalTo(postRequestMap.get("gender")));
        assertThat(dbMap.get("NAME"),equalTo(postRequestMap.get("name")));
        assertThat(dbMap.get("PHONE"),equalTo(postRequestMap.get("phone")));

        //disconnect from DB
        DB_Utils.destroy();

    }

}
