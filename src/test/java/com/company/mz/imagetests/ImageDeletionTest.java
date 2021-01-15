package com.company.mz.imagetests;

import com.company.mz.BaseTest;
import com.company.mz.endpoints.Endpoints;
import com.company.mz.util.UtilMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

//@Disabled
public class ImageDeletionTest extends BaseTest {

    @BeforeEach
    void setUp() {

        UtilMethods.simpleImageCreate();
    }

    @Test
    void authorizedDeleteTest() {
        given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .when()
                .delete(Endpoints.IMAGE_AUTH_DELETE, UtilMethods.imageHash)
                .then()
                .statusCode(200);
    }

    @Test
    void UnAuthorizedDeleteTest() {
        UtilMethods.imageDeleteHash = UtilMethods.receiveImageDeleteHash();
        given()
                .spec(UtilMethods.baseRequestSpecUnAuth)
                .when()
                .delete(Endpoints.IMAGE_UNAUTH_DELETE, UtilMethods.imageDeleteHash)
                .then()
                .log().all()
                .statusCode(403);
        UtilMethods.simpleImageDelete();
    }

}
