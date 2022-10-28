package com.cydeo.tests.day05_path_jasonpath;

import com.cydeo.utils.HRApiTestBase;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cydeo.utils.ConfigurationReader;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HREmployeesJsonPathTest extends HRApiTestBase {

    /**
     Given accept type is Json
     And query param limit is 200
     when I send GET request to /employees
     Then I can use jsonPath to query and read values from json body
     */
    @DisplayName("GET /employees?limit=200 => jsonPath filters")
            @Test
            public void jsonPathEmployeesTest(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit",200)
                .when().get("/employees");
        response.prettyPrint();
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        //convert/parse json response body to jsonpath object
        JsonPath jsonPath = response.jsonPath();

        System.out.println("First emp firstname = " + jsonPath.getString("items[0].first_name"));
        System.out.println("First emp job id = " + jsonPath.getString("items[0].job_id"));

        //Working withJsonPath lists:
        //get all the emails into a list anf print out
        List<String> emails = jsonPath.getList("items.email");
        System.out.println("emails = " + emails);

        //assert email contains TGATES
        assertTrue(emails.contains("TGATES"));

        //get all employees names who work for department 90
        List<String> nameAtDept90 = jsonPath.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println("namesAtDept90 = " + nameAtDept90);
        System.out.println("namesAtDept90.size() = " + nameAtDept90.size());

        //get all employee first names who work as IT_PROG
        List<String> itProgs = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");

        //get all employee first names whose salary is more than or equal 5000
        List<String> salaryMoreThan5000 = jsonPath.getList("items.findAll{it.salary>=5000}.first_name");

        //get emp first name who has max salary
        List<String> maxSalary = jsonPath.getList("items.max{it.salary}.first_name");

        //get emp first name who has min salary
        List<String> minSalary = jsonPath.getList("items.min{it.salary}.first_name");

    }


}
