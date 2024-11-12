package tests;

import POM.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutTests {
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
    private CheckoutCompletePage checkoutCompletePage;

    @BeforeEach
    public void setup(){
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
        checkoutCompletePage = new CheckoutCompletePage(driver, wait);

        String username = "standard_user";
        String passwordForAll = "secret_sauce";
        firstName = "firstname";
        lastName = "lastname";
        zipCode = "1234";

        driver.get("https://www.saucedemo.com/");
        loginPage.loginProcess(username, passwordForAll);
    }

    @Test
    public void checkoutWithEmptyCartTest(){
        shopPage.clickShoppingCartButton();
        yourCartPage.goToCheckout();
        checkoutInfoPage.fillForm(firstName, lastName, zipCode);
        checkoutInfoPage.submitFormContinue();
        checkoutOverviewPage.clickFinishButton();

        Assertions.assertFalse(checkoutCompletePage.getBackHomeButton().isDisplayed(), "Checkout Complete - process should not be successful, because cart is empty!");
    }

    @Test
    public void checkoutWithOneItemInCart(){
        shopPage.getAddToCartButtonByIndex(1);
        shopPage.clickShoppingCartButton();
        yourCartPage.goToCheckout();
        checkoutInfoPage.fillForm(firstName, lastName, zipCode);
        checkoutInfoPage.submitFormContinue();
        checkoutOverviewPage.clickFinishButton();

        Assertions.assertTrue(checkoutCompletePage.getBackHomeButton().isDisplayed(), "Checkout Complete - process should be successful with valid data and one item!");
    }

    @Test
    public void checkoutWithTwoItemInCart(){
        shopPage.getAddToCartButtonByIndex(1);
        shopPage.getAddToCartButtonByIndex(2);
        shopPage.clickShoppingCartButton();
        yourCartPage.goToCheckout();
        checkoutInfoPage.fillForm(firstName, lastName, zipCode);
        checkoutInfoPage.submitFormContinue();
        checkoutOverviewPage.clickFinishButton();


        Assertions.assertTrue(checkoutCompletePage.getBackHomeButton().isDisplayed(), "Checkout Complete - process should be successful with valid data and two items!");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
