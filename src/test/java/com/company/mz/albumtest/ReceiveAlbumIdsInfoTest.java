package com.company.mz.albumtest;

import com.company.mz.dto.AlbumIdsResponse;
import com.company.mz.dto.AlbumIdsResponseUnAuth;
import com.company.mz.dto.AlbumInfoResponse;
import com.company.mz.dto.AlbumInfoResponseUnAuth;
import com.company.mz.endpoints.Endpoints;
import com.company.mz.util.UtilMethods;
import org.junit.jupiter.api.*;
import com.company.mz.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

//@Disabled
public class ReceiveAlbumIdsInfoTest extends BaseTest {

    @BeforeEach
    void setUp() {
        UtilMethods.simpleAlbumCreate();

    }

    @Test
    void authedAlbumIdsReceivingTest() {
        AlbumIdsResponse response = given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .expect()
                .body("success", is(true))
                .when()
                .get(Endpoints.ALBUM_IDS_INFO, username)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(AlbumIdsResponse.class);
        assertThat(response.getStatus(), is(200));
        assertThat(response.getSuccess(), is(true));
    }

    @Test
    void unAuthedAlbumIdsreceivingTest() {
        AlbumIdsResponseUnAuth responseUnAuth = given()
                .spec(UtilMethods.baseRequestSpecUnAuth)
                .when()
                .get(Endpoints.ALBUM_IDS_INFO, username)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(AlbumIdsResponseUnAuth.class);

        assertThat(responseUnAuth.getSuccess(), is(false));
        assertThat(responseUnAuth.getStatus(), is(403));
    }

    @Test
    void authedAlbumsInfo() {
                given()
                .spec(UtilMethods.baseRequestSpecAuth)
                .expect()
                .body("data[0].account_url", equalTo(username))
                .body("success", is(true))
                .when()
                .get(Endpoints.ALBUM_INFO, username)
                .then()
                .statusCode(200);
//                .extract().body().as(AlbumInfoResponse.class);
    }

    @Test
    void unAuthedAlbumsInfo() {
        AlbumInfoResponseUnAuth responseUnAuth =
                given()
                .spec(UtilMethods.baseRequestSpecUnAuth)
                .expect()
                .body("status", equalTo(403))
                .body("success", is(false))
                .when()
                .get(Endpoints.ALBUM_INFO, username)
                .then()
                .extract()
                .body()
                .as(AlbumInfoResponseUnAuth.class);

        assertThat(responseUnAuth.getStatus(), is(403));
        assertThat(responseUnAuth.getSuccess(), is(false));
    }

    @AfterEach
    void afterAll() {
        UtilMethods.simpleAlbumDelete();
    }
}
