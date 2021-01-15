package com.company.mz.albumtest;

import com.company.mz.endpoints.Endpoints;
import com.company.mz.util.UtilMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.company.mz.BaseTest;

import static io.restassured.RestAssured.given;

//@Disabled
public class AlbumDeletionTest extends BaseTest {

    @BeforeEach
    void setUp() {
        UtilMethods.simpleAlbumCreate();
    }

    @Test
    void authedDeleteTest() {
        given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .when()
                .delete(Endpoints.ALBUM_AUTH_DELETE, UtilMethods.albumHash)
                .then()
                .statusCode(200);
    }

    @Test
    void unAuthedDeleteTest() {
        given()
                .spec(UtilMethods.baseRequestSpecUnAuth)
                .when()
                .delete(Endpoints.ALBUM_UNAUTH_DELETE, UtilMethods.albumDeleteHash)
                .then()
                .statusCode(403);
        UtilMethods.simpleAlbumDelete();
    }
}



