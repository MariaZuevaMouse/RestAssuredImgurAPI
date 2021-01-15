package com.company.mz.imagetests;

import com.company.mz.dto.ImageInfo;
import com.company.mz.endpoints.Endpoints;
import com.company.mz.util.UtilMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.company.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

//@Disabled
public class UpdateGetInfoFavoriteImageTest extends BaseTest {

    @BeforeEach
    void setUp() {
        UtilMethods.simpleImageCreate();
    }

    @Test
    void FavoriteAnImageTest() {
        given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .when()
                .post(Endpoints.IMAGE_FAVORITE, UtilMethods.imageHash)
                .then()
                .statusCode(200);
    }

    @Test
    void UpdateImageInfo() {
        ImageInfo response =
        given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .when()
                .post(Endpoints.IMAGE_UPDATE, UtilMethods.imageHash)
                .prettyPeek()
                .then()
        .extract().body().as(ImageInfo.class);
        assertThat(response.getStatus(), is(200));
        assertThat(response.getSuccess(), is(true));
    }

    @Test
    void imageInfoTest() {
        given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .when()
                .expect()
                .body("data.id", is(UtilMethods.imageHash))
                .body("success", is(true))
                .body("status", is(200))
                .when()
                .get(Endpoints.IMAGE_INFO, UtilMethods.imageHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }



    @AfterEach
    void tearDown() {
        UtilMethods.simpleImageDelete();
    }


}
