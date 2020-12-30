package ru.mz;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class BaseTest {
    protected static Properties props = new Properties();
    protected static String token;
    protected static String username;
    protected static Map<String, String> headers = new HashMap<>();


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
}
