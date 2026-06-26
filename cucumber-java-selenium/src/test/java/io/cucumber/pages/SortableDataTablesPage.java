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
    @FindBy(xpath = "//table[@id='table1']/thead/tr")
    private List<WebElement> example1Columns;
    @FindBy(xpath = "//table[@id='table1']/tbody/tr")
    private List<WebElement> example1Rows;

    public SortableDataTablesPage(ChromeDriver driver) {
        super(driver);
    }

    public String getExample1HeaderText() {
        return getElement(example1Header).getText();
    }

    public List<List<String>> getExample1RowTexts() {
        List<WebElement> columnElements = getAllElement(example1Columns);
        List<WebElement> rowElements = getAllElement(example1Rows);

        // Extract text from column headers
        List<List<String>> columnHeaderTexts = columnElements.stream()
                .map(row -> row.findElements(By.tagName("th")).stream()
                        .map(cell -> cell.getText().trim())
                        // FILTER: Only store text if it does NOT contain "Action"
                        .filter(text -> !text.contains("Action"))
                        .collect(Collectors.toList()))
                .toList();


        // Extract text from every row cell
        List<List<String>> rowTexts = new java.util.ArrayList<>(rowElements.stream()
                .map(row -> row.findElements(By.tagName("td")).stream()
                        .map(cell -> cell.getText().trim())
                        // FILTER: Only store cell text if it does NOT contain "edit"
                        .filter(text -> !text.contains("edit"))
                        .collect(Collectors.toList()))
                .toList());

        // append columnHeaderTexts list at the start of rowTexts List
        rowTexts.addAll(0, columnHeaderTexts);

        return rowTexts;
    }
}
