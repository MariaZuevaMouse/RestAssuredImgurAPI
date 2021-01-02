package ru.mz.albumtest;

import org.junit.jupiter.api.*;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ReceiveAlbumIdsInfoTest extends BaseTest {

    @BeforeEach
    void setUp() {
        simpleAlbumCreate();
    }

    @Test
    void authedAlbumIdsReceivingTest() {
        given()
                .headers("Authorization", token)
                .expect()
                .body("success", is(true))
                .when()
                .get("account/{username}/albums/ids/", username)
                .then()
                .statusCode(200);
    }

    @Test
    void unAuthedAlbumIdsreceivingTest() {
        given()
                .headers("Authorization", "Client-ID " + username)
                .when()
                .get("account/{username}/albums/ids", username)
                .then()
                .statusCode(403);
    }

    @Test
    void authedAlbumsInfo() {
        given()
                .headers("Authorization", token)
                .expect()
                .body("data[0].account_url", equalTo(username))
                .body("success", is(true))
                .when()
                .get("account/{username}/albums/", username)
                .then()
                .statusCode(200);
    }

    @Test
    void unAuthedAlbumsInfo() {
        given()
                .headers("Authorization", "Client-ID " + username)
                .expect()
                .body("status", equalTo(403))
                .body("success", is(false))
                .when()
                .get("account/{username}/albums/", username)
                .then()
//                .log().all();
                .statusCode(403);
    }

    @AfterEach
    void afterAll() {
        simpleAlbumDelete();
    }
}
