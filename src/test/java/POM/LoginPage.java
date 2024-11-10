package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    private WebElement findErrorMessage(){
        return driver.findElement(By.xpath("//h3[@data-test='error']"));
    }

    private WebElement getUsernameInput() {
        return driver.findElement(By.id("user-name"));
    }

    private WebElement getPasswordInput() {
        return driver.findElement(By.id("password"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));
    }


    // Method to login process
    public void loginProcess(String username, String password) {

        getUsernameInput().clear();
        getPasswordInput().clear();

        getUsernameInput().sendKeys(username);
        getPasswordInput().sendKeys(password);
        getLoginButton().click();
    }

    public WebElement getErrorMessage() {
        return findErrorMessage();
    }

    public boolean isLoginPageVisible() {
        wait.until(ExpectedConditions.visibilityOf(getLoginButton()));
        return getLoginButton().isDisplayed();
    }
}
