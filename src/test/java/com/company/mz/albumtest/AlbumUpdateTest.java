package com.company.mz.albumtest;

import com.company.mz.endpoints.Endpoints;
import com.company.mz.util.UtilMethods;
import io.restassured.builder.MultiPartSpecBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.company.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//@Disabled
public class AlbumUpdateTest extends BaseTest {
    String updateName;
    String updatedDesc;

    @BeforeEach
    void setUp() {
        UtilMethods.simpleAlbumCreate();
        updateName = testProps.getProperty("updateted.album.name");
        updatedDesc = testProps.getProperty("updated.album.description");
        multiPartSpecification = new MultiPartSpecBuilder(updateName).controlName("title").build();
        requestSpecification = UtilMethods.baseRequestSpecAuth.multiPart(multiPartSpecification).multiPart("description", updatedDesc);
    }

    @Test
    void updateAlbumTest() {
        given()
                .spec(requestSpecification)
                .expect()
                .body("data", is(true))
                .body("success", is(true))
                .when()
                .put(Endpoints.ALBUM_UPDATE, UtilMethods.albumHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @AfterEach
    void tearDown() {
        UtilMethods.simpleAlbumDelete();
    }
}
