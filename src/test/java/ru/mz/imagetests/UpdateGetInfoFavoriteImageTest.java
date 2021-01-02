package ru.mz.imagetests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UpdateGetInfoFavoriteImageTest extends BaseTest {

    @BeforeEach
    void setUp() {
        simpleImageCreate();
    }

    @Test
    void FavoriteAnImageTest() {
        given()
                .headers("Authorization", token)
                .when()
                .post("/image/{imageHash3}/favorite", imageHash)
                .then()
                .statusCode(200);
    }

    @Test
    void UpdateImageInfo() {
        given()
                .headers("Authorization", token)
                .when()
                .post("/image/{imageHash}", imageHash)
                .then()
                .statusCode(200);
    }

    @Test
    void imageInfoTest() {
        given()
                .headers("Authorization", token)
                .when()
                .expect()
                .body("data.id", is(imageHash))
                .body("success", is(true))
                .body("status", is(200))
                .when()
                .get("image/{imgehash}", imageHash)
                .then()
                .statusCode(200);
    }



    @AfterEach
    void tearDown() {
        simpleImageDelete();
    }


}
