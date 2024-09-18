package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class loginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Constructor
    public loginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Locators
    // SWAG Labs title
    private WebElement getSwagLabsTitle() {
        return driver.findElement(By.xpath("//a[text()='Swag Labs']"));
    }

    // username input
    private WebElement getUsernameInput() {
        return driver.findElement(By.id("user-name"));
    }

    // password input
    private WebElement getPasswordInput() {
        return driver.findElement(By.id("password"));
    }

    // Login button
    private WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));
    }

    // accepted usernames
    private WebElement getUsernameList() {
        return driver.findElement(By.id("login_credentials"));
    }

    // password
    private WebElement getPasswordList() {
        return driver.findElement(By.cssSelector("div.login_password"));
    }

    // Method to login process
    public void loginProcess(String username, String password) {
        driver.get("http://www.saucedemo.com/");

        // Fill the form
        getUsernameInput().sendKeys(username);
        getPasswordInput().sendKeys(password);
        getLoginButton().click();
    }
}
