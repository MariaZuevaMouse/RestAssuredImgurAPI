package ru.mz.imagetests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.mz.BaseTest;

import static io.restassured.RestAssured.given;

public class UploadThroughUrlTest extends BaseTest {

    @Test
    void addImageTest() {
        imageHash = given()
                .headers("Authorization", token)
                .log().all()
                .multiPart("image", props.getProperty("image.url" )) //)//"https://mir-s3-cdn-cf.behance.net/project_modules/disp/0c120d26942581.5604f7b2c2fd9.jpg"
                .when()
                .post("/image")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath().getString("data.id");
    }

    @AfterEach
    void tearDown() {
        given()
                .headers("Authorization", token)
                .multiPart("ids", imageHash)
                .when()
                .delete("/image/{imagehash}", imageHash )
                .then()
                .statusCode(200);
    }
}
