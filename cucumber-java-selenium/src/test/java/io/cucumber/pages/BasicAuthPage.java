package io.cucumber.pages;

import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPage extends Page {

    @FindBy(css = "div p")
    private WebElement loginMessage;

    public BasicAuthPage(ChromeDriver driver) {
        super(driver);
    }

    public void openWithCredentials(String url, String username, String password){
        String mainSiteUrl = url.replace("https://","")
                .replace("/basic_auth","");

        // Register the Basic Auth credentials for the main site
        driver.register(
                uri -> uri.getHost().contains(mainSiteUrl),
                UsernameAndPassword.of(username, password)
        );

        // Navigate to the intended site, and Selenium bypass the prompt seamlessly
        driver.get(url);
    }

    public String getLoginMessage() {
        return getElement(loginMessage).getText();
    }
}
