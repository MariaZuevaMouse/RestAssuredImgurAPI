package ru.mz.albumtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AlbumUpdateTest extends BaseTest {

    @BeforeEach
    void setUp() {
        simpleAlbumCreate();
    }

    @Test
    void updateAlbumTest() {
        given()
                .headers("Authorization", token)
                .multiPart("title", props.getProperty("updateted.album.name"))
                .multiPart("description", props.getProperty("updated.album.description"))
                .expect()
                .body("data", is(true))
                .body("success", is(true))
                .when()
                .put("album/{albumHash}", albumHash)
                .then()
                .statusCode(200);
    }

    @AfterEach
    void tearDown() {
        simpleAlbumDelete();
    }
}
