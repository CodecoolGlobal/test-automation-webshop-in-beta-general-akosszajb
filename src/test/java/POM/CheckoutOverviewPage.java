package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutOverviewPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @FindBy(css = "button[data-test='finish']")
    private WebElement finishButton;

    public void clickFinishButton() {
        finishButton.click();
    }
}
