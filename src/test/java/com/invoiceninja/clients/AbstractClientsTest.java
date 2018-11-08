package com.invoiceninja.clients;

import com.invoiceninja.BaseTest;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractClientsTest extends BaseTest {

    String clientsResource;

    @BeforeMethod
    public void setUpUrl() {
        clientsResource = properties.getProperty("api.resource.clients");
    }
}
