package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutCompletePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public WebElement getBackHomeButton() {
        return backHomeButton;
    }
}
