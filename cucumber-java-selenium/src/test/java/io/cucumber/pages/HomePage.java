package io.cucumber.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Page {

    @FindBy(css = "h1")
    private WebElement title;
    @FindBy(css = "h2")
    private WebElement header;
    @FindBy(css = "ul li a")
    private List<WebElement> exampleLinks;

    public HomePage(ChromeDriver driver) {
        super(driver);
        System.out.println("Homepage title is : " + getTitle().getText());
    }

    public WebElement getTitle() {
        return title;
    }

    public String getHeaderText() {
        return getElement(header).getText();
    }

    public List<String> getExampleLinkTexts() {
        List<String> linkTexts = new ArrayList<>();
        for (WebElement linkElement : getAllElement(exampleLinks)) {
            linkTexts.add(linkElement.getText().trim());
        }
        return linkTexts;
    }

    public void clickExampleLink(String link){
        getLink(link).click();
    }
}
