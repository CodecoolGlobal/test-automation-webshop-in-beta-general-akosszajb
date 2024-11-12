package tests;

import POM.LoginPage;
import POM.ShopPage;
import io.github.cdimascio.dotenv.Dotenv;
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
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ShopPage shopPage;

    private String passwordForAll;


    @BeforeEach
    public void setup(){
        Dotenv dotenv = Dotenv.load();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        loginPage = new LoginPage(driver, wait);
        shopPage = new ShopPage(driver, wait);

        passwordForAll = "secret_sauce";

        String url = dotenv.get("URL");
        driver.get(url);
    }

    @Test
    public void loginWithStandardUser(){
        String username = "standard_user";

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithLockedOutUser(){
        String username = "locked_out_user";

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(loginPage.getErrorMessage().isDisplayed(), "Error message is not displayed (Epic sadface: Sorry, this user has been locked out.)");
    }

    @Test
    public void loginWithProblemUser(){
        String username = "problem_user";

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithPerformanceGlitchUser(){
        String username = "performance_glitch_user";

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithErrorUser(){
        String username = "error_user";

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithVisualUser(){
        String username = "visual_user";

        loginPage.loginProcess(username, passwordForAll);

        Assertions.assertTrue(shopPage.getProductsTitle().isDisplayed(), "Products title is not displayed");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
