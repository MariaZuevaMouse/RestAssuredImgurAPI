package com.company.mz.imagetests;

import com.company.mz.BaseTest;
import com.company.mz.endpoints.Endpoints;
import com.company.mz.util.UtilMethods;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

//@Disabled
public class UploadBase64Test extends BaseTest {

    String encodedImage;
    String uploadedImageDelHash;

    @BeforeEach
    void setUp(){
        byte[] fileContent = getFileContent();
        encodedImage = Base64.getEncoder().encodeToString(fileContent);
        multiPartSpecification = new MultiPartSpecBuilder(encodedImage).controlName("image").build();
        requestSpecification = UtilMethods.baseRequestSpecAuth.multiPart(multiPartSpecification);

        RestAssured.requestSpecification = requestSpecification;

    }

    @Test
    void uploadFileTest() {
        uploadedImageDelHash = given()
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post(Endpoints.IMAGE_POST)
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

    @AfterEach
    void tearDown() {
        given()
                .multiPart("ids", uploadedImageDelHash)
                .when()
                .delete(Endpoints.IMAGE_AUTH_DELETE, uploadedImageDelHash )
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    private byte[] getFileContent() {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File( Objects.requireNonNull(classLoader.getResource("token.jpg").getFile()) );
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

}
