package com.company.mz.util;

import com.company.mz.endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UtilMethods {
    private static String mainProperties = "src/main/resources/mainapplication.properties";
    protected static Properties props = new Properties();

    protected static String username = "mzueva4037";

    public static String imageHash;
    public static String imageDeleteHash;
    protected static Response response;
    public static String albumHash;
    public static String albumDeleteHash;
    public static RequestSpecification baseRequestSpecAuth;
    public static RequestSpecification baseRequestSpecUnAuth;



    static {

//        loadProperties(mainProperties);
        loadProperties(mainProperties, props);
        baseRequestSpecAuth = new RequestSpecBuilder().addHeader("Authorization", props.getProperty("token")).build();
        baseRequestSpecUnAuth = new RequestSpecBuilder().addHeader("Authorization", "Client-ID " + username).build();
        RestAssured.baseURI = props.getProperty("base.url");
        RestAssured.baseURI = "https://api.imgur.com/3/";
    }

    public static void simpleImageDelete() {
        given()
                .spec(baseRequestSpecAuth)
                .multiPart("ids", imageHash)
                .when()
                .delete(Endpoints.IMAGE_AUTH_DELETE, imageHash )
                .then()
                .statusCode(200);
    }

    public static void simpleImageCreate() {
        imageHash = given()
//                .headers("Authorization", token)
                .spec(baseRequestSpecAuth)
                .multiPart("image", props.getProperty("simple.image"))
                .when()
                .post("/image")
                .then()
                .statusCode(200)
                .extract()
                .response().jsonPath().getString("data.id");
    }

    public static void simpleAlbumCreate() {
        response = given()
//                .headers("Authorization", token)
                .spec(baseRequestSpecAuth)
//                .multiPart("title", props.getProperty("album.en.name"))
                .multiPart("title", "simple_album")
                .when()
                .post("album")
                .then()
                .statusCode(200)
                .extract()
                .response();
        albumHash = response.path("data.id");
        albumDeleteHash = response.path("data.deletehash");
    }

    public static void simpleAlbumDelete(){
        given()
//                .headers("Authorization", token)
                .spec(baseRequestSpecAuth)
                .when()
                .delete(Endpoints.ALBUM_AUTH_DELETE, albumHash)
                .then()
                .statusCode(200);
    }

    public static void loadProperties() {
        try {
            props.load(new FileInputStream("src/main/resources/mainapplication.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProperties(String path, Properties props) {
        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getAlbumCount(){
        return given()
                .spec(baseRequestSpecAuth)
                .when()
                .get(Endpoints.ALBUM_COUNT, username)
                .then()
                .extract()
                .body()
                .jsonPath().getInt("data");
    }

    public static String receiveImageDeleteHash(){
        return given()
//                .headers("Authorization", token)
                .spec(baseRequestSpecAuth)
                .when()
                .get("image/{imgehash}", imageHash)
                .then()
                .extract().response().jsonPath().getString("data.deletehash");
    }

}
