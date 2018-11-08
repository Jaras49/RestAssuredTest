package com.invoiceninja;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {

    public static final String X_TOKEN = "X-Ninja-Token";
    private static final String CONFIG_FILE_NAME = "config.properties";

    protected Properties properties;
    private String apiAddress;

    @BeforeMethod
    public void setUp() throws IOException {
        properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        apiAddress = properties.getProperty("api.address");
    }

    protected String buildRequestUrl(String path) {
        return apiAddress + path;
    }

    protected String getToken() {
        return properties.getProperty("api.token");
    }
}
