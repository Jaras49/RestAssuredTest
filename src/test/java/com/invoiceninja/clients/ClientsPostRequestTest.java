package com.invoiceninja.clients;

import com.google.gson.JsonObject;
import com.invoiceninja.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ClientsPostRequestTest extends BaseTest {

    private String clientsResource;

    @Override
    @BeforeMethod
    public void setUp() throws IOException {
        super.setUp();
        clientsResource = properties.getProperty("api.resource.clients");
    }

    @Test
    public void shouldPostNewUser() {

        JsonObject postBody = new JsonObject();
        postBody.addProperty("name", "RestAssured");
        postBody.addProperty("city", "ViceCity");

        // @formatter:off

            given()
                    .header(BaseTest.X_TOKEN, getToken())
                    .body(postBody.toString())
                    .contentType(ContentType.JSON)
            .when()
                    .post(buildRequestUrl(clientsResource))
            .then()
                    .assertThat()
                        .spec(assertContentTypeAndResponseCode())
                        .body("data.name",equalTo("RestAssured"))
                        .body("data.city", equalTo("ViceCity" ));

        // @formatter:on
    }
}
