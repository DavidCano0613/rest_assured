package com.powerofvitality;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqResTest {

    @Test
    public void loginTest() {
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
                .log().all();
    }
}