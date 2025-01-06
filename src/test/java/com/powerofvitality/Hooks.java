package com.powerofvitality;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Hooks {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void get() {
        System.out.println("test1");
        given()
                .when()
                .get("users/2")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2));
    }

    @Test
    public void post() {
    }

    @Test
    public void patch() {
        String nameUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"daniela\",\n" +
                        "    \"job\": \"automation engineer\"\n" +
                        "}")
                .patch("users/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("name");

        assertThat(nameUpdated, equalTo("daniela"));
    }

    @Test
    public void put() {
        String nameUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"daniela\",\n" +
                        "    \"job\": \"automation engineer\"\n" +
                        "}")
                .put("users/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("job");

        assertThat(nameUpdated, equalTo("automation"));
    }

    @Test
    public void delete() {
        given()
                .when()
                .delete("users/2")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @AfterClass
    public void closure() {
        System.out.println("bye");
    }
}