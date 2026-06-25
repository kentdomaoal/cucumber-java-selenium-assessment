package io.cucumber.glue;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.pages.HomePage;
import util.JsonReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomeSteps extends Context {

    private final HomePage homePage;

    public HomeSteps(Manager manager) {
        super(manager);
        homePage = new HomePage(manager.getDriver());
    }

    @When("the {string} header is displayed")
    public void theHeaderIsDisplayed(String header) {
        String headerTxt =  homePage.getHeaderText();
        assertEquals(header, headerTxt,
                String.format("Header displayed is %s but expected %s", headerTxt, header));
    }

    @Then("the expected list of example links should be displayed correctly")
    public void theExpectedListOfExampleLinksShouldBeDisplayed() {
        List<String> actualLinks = homePage.getExampleLinkTexts();
        List<String> expectedExamplesLink = JsonReader.getExampleList();

        for (String expectedLink : expectedExamplesLink) {
            assertTrue(actualLinks.contains(expectedLink),
                    String.format("Missing expected homepage link: %s", expectedLink));
        }
    }

    @When("the {string} example is opened")
    public void whenLinkIsOpened(String link) {
        homePage.clickExampleLink(link);
    }

}