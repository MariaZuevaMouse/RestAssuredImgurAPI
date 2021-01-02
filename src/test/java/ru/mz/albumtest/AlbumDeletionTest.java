package ru.mz.albumtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AlbumDeletionTest extends BaseTest {

    @BeforeEach
    void setUp() {
        simpleAlbumCreate();
    }

    @Test
    void authedDeleteTest() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("album/{albumHash}", albumHash)
                .then()
                .statusCode(200);
    }

    @Test
    void unAuthedDeleteTest() {
        given()
                .headers("Authorization", "Client-ID " + username)
                .when()
                .delete("album/{albumDeleteHash}", albumDeleteHash)
                .then()
                .statusCode(403);
        simpleAlbumDelete();
    }

}



