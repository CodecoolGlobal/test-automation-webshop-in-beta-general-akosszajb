package tests;

import POM.LoginPage;
import POM.ShopPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageTests {
    private String username;
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ShopPage shopPage;

    private String passwordForAll;


    @BeforeEach
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        loginPage = new LoginPage(driver, wait);
        shopPage = new ShopPage(driver, wait);

        passwordForAll = System.getenv("PW_FOR_ALL");
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void loginWithStandardUser(){
        username = System.getenv("STANDARD_USER");

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithLockedOutUser(){
        username = System.getenv("LOCKED_OUT_USER");

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(loginPage.getErrorMessage().isDisplayed(), "Error message is not displayed (Epic sadface: Sorry, this user has been locked out.)");
    }

    @Test
    public void loginWithProblemUser(){
        username = System.getenv("PROBLEM_USER");

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithPerformanceGlitchUser(){
        username = System.getenv("PERFORMANCE_GLITCH_USER");

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithErrorUser(){
        username = System.getenv("ERROR_USER");

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithVisualUser(){
        username = System.getenv("VISUAL_USER");

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
