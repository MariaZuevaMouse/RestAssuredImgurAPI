package ru.mz;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {
    protected static Properties props = new Properties();
    protected static String token;
    protected static String username;
    protected static Map<String, String> headers = new HashMap<>();
    protected static String imageHash;
    protected Response response;
    protected String albumHash;
    protected String albumDeleteHash;


    @BeforeAll
    static void beforeAll(){
        loadProperties();
        token = props.getProperty("token");
        headers.put("Authorization", token);
        RestAssured.baseURI = props.getProperty("base.url");
        username = props.getProperty("username");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private static void loadProperties() {
        try {
            props.load(new FileInputStream("src/test/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void simpleImageDelete() {
        given()
                .headers("Authorization", token)
                .multiPart("ids", imageHash)
                .when()
                .delete("/image/{imagehash}", imageHash )
                .then()
                .statusCode(200);
    }

    protected void simpleImageCreate() {
        imageHash = given()
                .headers("Authorization", token)
                .multiPart("image", props.getProperty("simple.image"))
                .when()
                .post("/image")
                .then()
                .statusCode(200)
                .extract()
                .response().jsonPath().getString("data.id");
    }

    protected void simpleAlbumCreate() {
        response = given()
                .headers("Authorization", token)
                .multiPart("title", props.getProperty("album.en.name"))
                .when()
                .post("album")
                .then()
                .statusCode(200)
                .extract()
                .response();
        albumHash = response.path("data.id");
        albumDeleteHash = response.path("data.deletehash");
    }

    protected void simpleAlbumDelete(){
        given()
                .headers("Authorization", token)
                .when()
                .delete("album/{albumHash}", albumHash)
                .then()
                .statusCode(200);
    }
}
