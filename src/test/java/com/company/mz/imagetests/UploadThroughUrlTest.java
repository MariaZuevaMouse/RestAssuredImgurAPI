package com.company.mz.imagetests;

import com.company.mz.endpoints.Endpoints;
import com.company.mz.util.UtilMethods;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.company.mz.BaseTest;

import static io.restassured.RestAssured.given;

//@Disabled
public class UploadThroughUrlTest extends BaseTest {
    private String imageUrl;

    @BeforeEach
    void setUp() {
        imageUrl = testProps.getProperty("image.url");
        multiPartSpecification = new MultiPartSpecBuilder(imageUrl)
                .controlName("image")
                .build();
        requestSpecification = UtilMethods.baseRequestSpecAuth.multiPart(multiPartSpecification);
        RestAssured.requestSpecification = requestSpecification;
    }

    @Test
    void addImageTest() {
        UtilMethods.imageHash = given()
                .when()
                .post(Endpoints.IMAGE_POST)
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
                .multiPart("ids", UtilMethods.imageHash )
                .when()
                .delete(Endpoints.IMAGE_AUTH_DELETE, UtilMethods.imageHash )
                .then()
                .statusCode(200);
    }
}
