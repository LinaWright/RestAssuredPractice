package com.cydeo.tests.day08_hamcrest;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import lombok.experimental.StandardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;

public class SpartanHamcrestTest extends SpartanTestBase {
    /**
     * given accept type is json
     * and path id is 24
     * when I send ger request to /spartans/{id}
     * then status code is 200
     * and content type is application/json
     * and
     * "id": 24,
     * "name": "Julio",
     * "gender": "Male",
     * "phone": 9393139934
     */
    @DisplayName("Get /spartans/{id} -> hamcrest assertions")
    @Test
    public void singleSpartanTest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 24)
                .when().get("/spartans/{id}")
                .then().statusCode(is(200))
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("id", is(24),
                        "name", equalTo("Julio"),
                        "gender", is("Male"),
                        "phone", is(9393139934L));
    }

    /**
     * Given accept type is json
     * And query param nameContains value is "e"
     * And query param gender value is "Female"
     * When I send get request to /spartans/search
     * Then status code is 200
     * And content type is Json
     * And json response data is as expected
     * totalElement is 34
     * ids has 94,98,91,81
     * has names "Elita","Marylee","Fenelia"
     * every gender is Female
     * every name contains "e"
     */

    @DisplayName("Get /spartans/search -> hamcrest assertions")
    @Test
    public void searchTest() {
        given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "e")
                .and().queryParams("gender", "female")
                .when().get("/spartans/search")
                .then().statusCode(is(200))
                .and().contentType(ContentType.JSON)
                .and().header("Date", containsString(LocalDate.now().getYear() + ""))
                .and().header("Date", containsString("2022"))
                .and().body("totalElement", is(equalTo(33)),
                        "content.id", hasItems(94, 98, 91, 81),
                        "content.name", hasItems("Elita", "Marylee", "Fenelia"),
                        "content.gender", everyItem(is("Female")),
                        "content.name", everyItem(containsStringIgnoringCase("e"))).log().all();

    }
}
