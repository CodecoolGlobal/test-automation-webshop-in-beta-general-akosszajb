package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutOverviewPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private WebElement getFinishButton() {
        return driver.findElement(By.cssSelector("button[data-test='finish']"));
    }

    public void clickFinishButton() {
        getFinishButton().click();
    }
}
