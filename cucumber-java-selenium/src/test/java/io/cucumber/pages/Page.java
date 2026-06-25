package io.cucumber.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

  protected ChromeDriver driver;
  private static final Duration TIMEOUT = Duration.ofSeconds(10);

  public Page(ChromeDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    waitForPageLoad();
  }

  public void waitForPageLoad() {
    try {
      Thread.sleep(Duration.ofSeconds(5));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public WebElement getElement(WebElement element){
      return new WebDriverWait(driver, TIMEOUT)
              .until(ExpectedConditions.visibilityOf(element));
  }

  public List<WebElement> getAllElement(List<WebElement> elements){
      return new WebDriverWait(driver, TIMEOUT)
              .until(ExpectedConditions.visibilityOfAllElements(elements));
  }

  public WebElement getLink(String link){
      return new WebDriverWait(driver, TIMEOUT)
              .until(ExpectedConditions.elementToBeClickable(By.linkText(link)));
  }
}