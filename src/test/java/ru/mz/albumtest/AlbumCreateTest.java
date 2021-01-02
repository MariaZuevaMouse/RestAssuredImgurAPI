package ru.mz.albumtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.mz.BaseTest;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class AlbumCreateTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("albumNames")
    void albumEnNameTest(String albumName) {
        albumHash = given()
                .headers("Authorization", token)
                .multiPart("title", albumName)
                .multiPart("description", props.getProperty("album.simple.description"))
                .when()
                .post("album")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

    private static Stream<String> albumNames(){
        return Stream.of(
                props.getProperty("album.en.name"),
                props.getProperty("album.ru.name"),
                props.getProperty("album.chinese.name"),
                props.getProperty("album.spec.symbol.name"),
                props.getProperty("album.empty.name"));
    }

    @AfterEach
    void tearDown() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("/album/{albumHash}", albumHash)
                .then()
                .statusCode(200);
    }
}
