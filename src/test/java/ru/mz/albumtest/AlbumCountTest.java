package ru.mz.albumtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AlbumCountTest extends BaseTest {
    int initialCount, updatedCount;

    @BeforeEach
    void setUp() {
        initialCount = getAlbumCount();
        simpleAlbumCreate();
        updatedCount = getAlbumCount();
    }

    @Test
    void getAlbumCountTest(){
        assertThat(updatedCount-initialCount, equalTo(1));
    }

    @AfterEach
    void tearDown() {
        simpleAlbumDelete();
    }

    private int getAlbumCount(){
        return given()
                .header("Authorization", token)
                .when()
                .get("account/{username}/albums/count", username)
                .then()
                .extract()
                .body()
                .jsonPath().getInt("data");
    }
}
