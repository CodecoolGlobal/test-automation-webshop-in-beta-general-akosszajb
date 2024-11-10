package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourCartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = "div[class='cart_list']")
    private WebElement cartList;

    public YourCartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void goToCheckout() {
        wait.until(ExpectedConditions.visibilityOf(cartList));
        checkoutButton.click();
    }
}
