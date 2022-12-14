package com.cydeo.tests.day11_put_patch_request;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
public class SpartanPUTRequestTest extends SpartanTestBase {
    /**
     Given accept type is json
     And content type is json
     And path param id is 15
     And json body is
     {
     "gender": "Male",
     "name": "PutRequest",
     "phone": 1234567425
     }
     When i send PUT request to /spartans/{id}
     Then status code 204
     */
    @DisplayName("PUT /spartans/{id}")
    @Test
    public void updateSpartanTest() {
        Map<String, Object>requestMap = new HashMap<>();
        requestMap.put("gender","Male");
        requestMap.put("name","PutRequest");
        requestMap.put("phone",1234567425L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 15)
                .and().body(requestMap)
                .when().put("/spartans/{id}");

        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        assertThat(response.getStatusCode(),equalTo(204));

    }

}
