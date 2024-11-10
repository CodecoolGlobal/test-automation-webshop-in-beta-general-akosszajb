package tests;

import POM.CheckoutInfoPage;
import POM.LoginPage;
import POM.ShopPage;
import POM.YourCartPage;
import POM.CheckoutOverviewPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.io.IOException;

public class CheckoutTests {
    private String username;
    private String passwordForAll;
    private String firstName;
    private String lastName;
    private String zipCode;
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ShopPage shopPage;
    private YourCartPage yourCartPage;
    private CheckoutInfoPage checkoutInfoPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private WebElement backHomeButton; 
      
    @BeforeEach
    public void setup() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        
        loginPage = new LoginPage(driver, wait);
        shopPage = new ShopPage(driver, wait);
        yourCartPage = new YourCartPage(driver, wait);
        checkoutInfoPage = new CheckoutInfoPage(driver, wait);
        checkoutOverviewPage = new CheckoutOverviewPage(driver, wait);
        
        username = System.getenv("STANDARD_USER");
        passwordForAll = System.getenv("PW_FOR_ALL");
        firstName = System.getenv("FIRST_NAME");
        lastName = System.getenv("LAST_NAME");
        zipCode = System.getenv("ZIP_CODE");

        driver.get("http://www.saucedemo.com/");
        loginPage.loginProcess(username, passwordForAll);
    }
    
    @Test
    public void checkoutWithEmptyCartTest() throws IOException {

        shopPage.clickShoppingCartButton();
        yourCartPage.goToCheckout();
        checkoutInfoPage.fillForm(firstName, lastName, zipCode);
        checkoutInfoPage.submitFormContinue();
        checkoutOverviewPage.clickFinishButton();
        
        backHomeButton = driver.findElement(By.id("back-to-products"));

        Assertions.assertFalse(backHomeButton.isDisplayed(), "Checkout Complete - process should not be successful, because cart is empty!");
    }

    @Test
    public void checkoutWithOneItemInCart() throws IOException {
       shopPage.getAddToCartButtonByIndex(1);
        shopPage.clickShoppingCartButton();
        yourCartPage.goToCheckout();
        checkoutInfoPage.fillForm(firstName, lastName, zipCode);
        checkoutInfoPage.submitFormContinue();
        checkoutOverviewPage.clickFinishButton();

        backHomeButton = driver.findElement(By.id("back-to-products"));

        Assertions.assertTrue(backHomeButton.isDisplayed(), "Checkout Complete - process should be successful with valid data and one item!");
    }
    @Test
    public void checkoutWithTwoItemInCart() throws IOException {
        shopPage.getAddToCartButtonByIndex(1);
        shopPage.getAddToCartButtonByIndex(2);
        shopPage.clickShoppingCartButton();
        yourCartPage.goToCheckout();
        checkoutInfoPage.fillForm(firstName, lastName, zipCode);
        checkoutInfoPage.submitFormContinue();
        checkoutOverviewPage.clickFinishButton();

        backHomeButton = driver.findElement(By.id("back-to-products"));

        Assertions.assertTrue(backHomeButton.isDisplayed(), "Checkout Complete - process should be successful with valid data and two items!");
    }
   
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
