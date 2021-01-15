package com.company.mz.accounttest;

import com.company.mz.endpoints.Endpoints;
import com.company.mz.dto.AccountInfoResponse;
import com.company.mz.util.UtilMethods;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.company.mz.BaseTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AccountTest extends BaseTest {
//    private ResponseSpecification responseSpecification;

    @BeforeEach
    void setUp() {
        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("X-Frame-Options", "DENY")
                .expectResponseTime(Matchers.lessThan(5000l))
                .expectStatusCode(200)
                .build();
    }

    @Test
    void getAccountInfoTest(){
        given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .when()
                .get(Endpoints.ACCOUNT_INFO, username)
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getAccountInfoWithLoggingTest(){
        AccountInfoResponse accountResponse = given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .log()
                .all()
                .when()
                .get(Endpoints.ACCOUNT_INFO, username)
                .prettyPeek()
                .then()
                .extract()
                .body().as(AccountInfoResponse.class);
        assertThat(accountResponse.getData().getUrl(), containsString(username));
        assertThat(accountResponse.getSuccess(), is(true));
        assertThat(accountResponse.getData().getIsBlocked(), is(false));

    }

    @Test
    void getAccountInfoWithoutToken() {
                when()
                .get(Endpoints.ACCOUNT_INFO, username)
                .prettyPeek()
                .then()
                .statusCode(401)
                .body("data.error", is("Authentication required"));
    }

    @Test
    void getAccountInfoVerifyUrlTest() {
        final String url = given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .log()
                .uri()
                .when()
                .get(Endpoints.ACCOUNT_INFO, username)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .log()
                .status()
                .extract()
                .response()
                .jsonPath()
                .getString("data.url");
        assertThat(url,equalTo(username));
    }

    @Test
    void getAccountInfoVerifyUrInGivenPartTest() {
        final String url = given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .log()
                .uri()
                .expect()
                .body("success",is(true))
                .body("data.url", is(username))
                .when()
                .get(Endpoints.ACCOUNT_INFO, username)
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
