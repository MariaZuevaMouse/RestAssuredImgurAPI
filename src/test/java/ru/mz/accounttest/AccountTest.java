package ru.mz.accounttest;

import org.junit.jupiter.api.Test;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AccountTest extends BaseTest {
    @Test
    void getAccountInfoTest(){
        given()
                .headers("Authorization", token)
                .when()
                .get("/account/{user}", username)
                .then()
                .statusCode(200);
    }

    @Test
    void getAccountInfoWithLoggingTest(){
        given()
                .headers(headers)
                .log()
//                .method()
                .all()
                .when()
                .get("https://api.imgur.com/3/account/mzueva4037")
                .prettyPeek()
                .then()
                .statusCode(200)
                .log()
                .status();
    }

    @Test
    void getAccountInfoWithoutToken() {
                when()
                .get("https://api.imgur.com/3/account/mzueva4037")
                .then()
                .statusCode(401);
    }

    @Test
    void getAccountInfoVerifyUrlTest() {
        final String url = given()
                .headers(headers)
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/mzueva4037")
//                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json")
                .log()
                .status()
                .extract()
                .response()
                .jsonPath()
                .getString("data.url");
        assertThat(url,equalTo("mzueva4037"));
    }

    @Test
    void getAccountInfoVerifyUrInGivenPartTest() {
        final String url = given()
                .headers(headers)
                .log()
                .uri()
                .expect()
                .body("success",is(true))
                .body("data.url", is("mzueva4037"))
                .when()
                .get("https://api.imgur.com/3/account/mzueva4037")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .log()
                .status()
                .extract()
                .response()
                .jsonPath()
                .getString("data.url");

    }
}
