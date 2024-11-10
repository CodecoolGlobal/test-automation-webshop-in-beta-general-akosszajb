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

    private WebElement findErrorMessage(){
        return driver.findElement(By.xpath("//h3[@data-test='error']"));
    }

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;


    public void loginProcess(String username, String password) {
        usernameInput.clear();
        passwordInput.clear();

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        loginButton.click();
    }

    public WebElement getErrorMessage() {
        return findErrorMessage();
    }

    public boolean isLoginPageVisible() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return loginButton.isDisplayed();
    }
}
