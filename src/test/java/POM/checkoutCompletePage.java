package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkoutCompletePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public checkoutCompletePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    @FindBy(css = ".pony_express")
    private WebElement ponyExpressImage;
    
    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    // Page Methods
    public boolean isPonyExpressImageDisplayed() {
        return ponyExpressImage.isDisplayed();
    }

    public void clickBackHomeButton() {
        backHomeButton.click();
    }
}
