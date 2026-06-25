package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.pages.BasicAuthPage;
import util.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicAuthSteps extends Context {

    private final BasicAuthPage basicAuthPage;

    public BasicAuthSteps(Manager manager) {
        super(manager);
        basicAuthPage = new BasicAuthPage(manager.getDriver());
    }

    @And("valid credentials are supplied")
    public void whenValidCredentialsAreSupplied() {
        basicAuthPage.openWithCredentials(
                ConfigReader.getProperty("BASIC_AUTH_URL"),
                ConfigReader.getProperty("BASIC_AUTH_USERNAME"),
                ConfigReader.getProperty("BASIC_AUTH_PASSWORD"));
    }

    @Then("{string} should be displayed")
    public void thenMessageIsDisplayed(String message) {
        String actualMessage = basicAuthPage.getLoginMessage();
        assertTrue(actualMessage.contains(message),
                "Expected success message is displayed");
    }
}
