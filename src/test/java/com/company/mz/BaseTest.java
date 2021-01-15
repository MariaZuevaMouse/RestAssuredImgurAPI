package com.company.mz;

//import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {
    protected ResponseSpecification responseSpecification = null;
    protected MultiPartSpecification multiPartSpecification= null;
    protected  RequestSpecification requestSpecification= null;
    protected static Properties testProps = new Properties();
    private static String testPropertiesPath = "src/test/resources/application.properties";
    protected static String token;
    protected static String username;

    @BeforeAll
    static void beforeAll(){
        loadProperties();
//        UtilMethods.loadProperties(testPropertiesPath, testProps);
        token = testProps.getProperty("token");
        RestAssured.baseURI = testProps.getProperty("base.url");
        username = testProps.getProperty("username");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//                , new AllureRestAssured());
    }

    private static void loadProperties() {
        try {
            testProps.load(new FileInputStream("src/test/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
