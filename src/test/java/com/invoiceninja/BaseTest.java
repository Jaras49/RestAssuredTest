package com.invoiceninja;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {

    public static final String X_TOKEN = "X-Ninja-Token";
    private static final String CONFIG_FILE_NAME = "config.properties";

    protected Properties properties;

    @BeforeMethod
    public void setUp() throws IOException {
        properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
    }

    protected ResponseSpecification assertContentTypeAndResponseCode() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

    protected String buildRequestUrl(String path) {
        return new StringBuilder(properties.getProperty("api.address"))
                .append(path)
                .toString();
    }

    protected String getToken() {
        return properties.getProperty("api.token");
    }
}
