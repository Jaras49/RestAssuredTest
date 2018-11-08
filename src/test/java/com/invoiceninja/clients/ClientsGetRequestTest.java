package com.invoiceninja.clients;

import com.invoiceninja.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ClientsGetRequestTest extends AbstractClientsTest {

    @Test
    public void shouldGetClients() {
        // @formatter:off

        given()
                .header(BaseTest.X_TOKEN, getToken())
        .when()
                .get(buildRequestUrl(clientsResource))
        .then()
                .assertThat()
                .spec(assertContentTypeAndResponseCode());

        // @formatter:on
    }

    @Test(dataProvider = "data")
    public void shouldGetClientWithId(int id, String name, String city) {
        // @formatter:off

        given()
                .header(BaseTest.X_TOKEN, getToken())
        .when()
                .get(buildRequestUrl(clientsResource) + "/" + id)
        .then()
                .assertThat()
                    .spec(assertContentTypeAndResponseCode())
                    .body("data.id",  equalTo(id))
                    .body("data.name", equalTo(name))
                    .body("data.city", equalTo(city));

        // @formatter:on
    }

    @DataProvider(name = "data")
    public Object[][] createTestData() {
        return new Object[][]{
                {646, "Test-01", "Kraków"},
                {645, "Testeer", "Bytom"},
                {635, "Tester111165985328853412523718875726248384295841499825877723856725532159957573767", "Poznan"}
        };
    }
}
