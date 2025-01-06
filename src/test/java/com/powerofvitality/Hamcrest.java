package com.powerofvitality;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest {
    @Test
    public void assertWithHamcrest() {
        given()
                .contentType(ContentType.JSON)
                .body(
                        """
                                {
                                    "email": "eve.holt@reqres.in",
                                    "password": "cityslicka"
                                }
                                """
                )
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("token", notNullValue());

    }

    @Test
    public void assertWithHamcrestTwo() {
        given()
                .contentType(ContentType.JSON)
                .body(
                        """
                                {
                                    "email": "eve.holt@reqres.in",
                                    "password": "cityslicka"
                                }
                                """
                )
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("token", nullValue());

    }

    @Test
    public void assertWithHamcrestThree() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("data.id", equalTo(3));
    }

    @Test
    public void assertWithHamcrestFour() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsStringIgnoringCase("JANET"))
                .body("data.email", equalToIgnoringCase("janet.weaver@reqres.in"))
                .body("data.id", lessThan(3))
                .body("data.id", greaterThanOrEqualTo(2));
    }

    @Test
    public void testListWithHamcrest() {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");

        assertThat(fruits, hasSize(3));
        assertThat(fruits, contains("Apple", "Banana", "Orange"));
        assertThat(fruits, hasItem("Banana"));
    }
}