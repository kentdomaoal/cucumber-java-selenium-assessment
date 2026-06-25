package io.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SortableDataTablesPage extends Page {

    @FindBy(xpath = "//h4[text()='Example 1']")
    private WebElement example1Header;
    @FindBy(xpath = "//table[@id='table1']/tbody/tr")
    private List<WebElement> example1Rows;

    public SortableDataTablesPage(ChromeDriver driver) {
        super(driver);
    }

    public String getExample1HeaderText() {
        return getElement(example1Header).getText();
    }

    public List<List<String>> getExample1RowTexts() {
        List<WebElement> rowElements = getAllElement(example1Rows);

        // Extract text from every cell, mapping them row-by-row
        return rowElements.stream()
                .map(row -> row.findElements(By.tagName("td")).stream()
                        .map(cell -> cell.getText().trim())
                        // FILTER: Only store cell text if it does NOT contain "edit"
                        .filter(text -> !text.contains("edit"))
                        .collect(Collectors.toList()))
                .toList();
    }
}
