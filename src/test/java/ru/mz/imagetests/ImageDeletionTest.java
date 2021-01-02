package ru.mz.imagetests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ImageDeletionTest extends BaseTest {
    String imageDeleteHash;
    @BeforeEach
    void setUp() {
        simpleImageCreate();
    }

    @Test
    void authorizedDeleteTest() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("image/{imageHash}", imageHash)
                .then()
                .statusCode(200);
    }

    @Test
    void UnAuthorizedDeleteTest() {
        imageDeleteHash = receiveImageDeleteHash();
        given()
                .headers("Authorization", "Client-ID " +username)
                .when()
                .delete("/image/{imageDeleteHash}", imageDeleteHash)
                .then()
                .log().all()
                .statusCode(403);
        simpleImageDelete();
    }

    private String receiveImageDeleteHash(){
        return given()
                .headers("Authorization", token)
                .when()
                .get("image/{imgehash}", imageHash)
                .then()
                .extract().response().jsonPath().getString("data.deletehash");
    }
}
