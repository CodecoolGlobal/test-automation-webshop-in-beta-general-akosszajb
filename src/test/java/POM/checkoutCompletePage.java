package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class checkoutCompletePage {

    private WebDriver driver;

    // Constructor
    public checkoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By ponyExpressImage = By.cssSelector(".pony_express");
    private By backHomeButton = By.id("back-to-products");

    // Page Methods
    public boolean isPonyExpressImageDisplayed() {
        WebElement image = driver.findElement(ponyExpressImage);
        return image.isDisplayed();
    }

    public void clickBackHomeButton() {
        WebElement button = driver.findElement(backHomeButton);
        button.click();
    }
}
