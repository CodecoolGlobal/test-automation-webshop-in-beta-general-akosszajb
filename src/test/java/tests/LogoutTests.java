package tests;

import POM.LoginPage;
import POM.Navbar;
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

public class LogoutTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage login;
    private Navbar navbar;

    @BeforeEach
    public void setup() {
        Dotenv dotenv = Dotenv.load();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        login = new LoginPage(driver, wait);
        navbar = new Navbar(driver, wait);

        String url = dotenv.get("URL");
        driver.get(url);
    }

    @Test
    public void logoutSuccess() {
        login.loginProcess("standard_user", "secret_sauce");
        navbar.clickReactMenuButton();
        navbar.clickLogoutLink();

        Assertions.assertTrue(login.isLoginPageVisible());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
