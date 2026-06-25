package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.pages.SortableDataTablesPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SortableDataTablesSteps extends Context {

    private final SortableDataTablesPage sortableDataTablesPage;

    public SortableDataTablesSteps(Manager manager) {
        super(manager);
        sortableDataTablesPage = new SortableDataTablesPage(manager.getDriver());
    }

    @And("the {string} table header is displayed")
    public void theHeaderIsDisplayed(String header) {
        String headerTxt =  sortableDataTablesPage.getExample1HeaderText();
        assertEquals(header, headerTxt,
                String.format("Header displayed is %s but expected %s", headerTxt, header));
    }

    @Then("the {string} table should display the following records:")
    public void thenRowsOfTableAreDisplayed(String header, DataTable dataTable) {
        List<List<String>> actualData = sortableDataTablesPage.getExample1RowTexts();
        assertFalse(actualData.isEmpty(), String.format("Expected rows of %s table is displayed", header));

        // Converts the Gherkin table into a List<List<String>>
        List<List<String>> expectedData = dataTable.asLists(String.class);

        assertEquals(expectedData, actualData, "The web table content does not match expected data.");
    }
}
