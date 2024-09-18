import POM.loginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.time.Duration;
import java.io.BufferedReader;
import java.io.IOException;

public class LoginPageTests {
    private String username;
    private String password;
    private WebDriver driver;
    private WebDriverWait wait;
    private loginPage _loginPage;
    private WebElement productTitle;
    WebElement errorMessage;
    
    private String[] readCredentialsFromFile(int usernameLineNumber) throws IOException {
        String username = null;
        String password = null;


        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("credentials.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            password = reader.readLine();
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == usernameLineNumber) {
                    username = line;
                    break;
                }
            }
        }

        return new String[]{username, password}; // Return the username and password
    }

    @BeforeEach
    public void setup() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("chromedriverpath.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String chromeDriverPath = reader.readLine();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        _loginPage = new loginPage(driver);
    }

    @Test
    public void loginWithStandardUser() throws IOException {
        String[] credentials = readCredentialsFromFile(2);  // 2nd line is standard_user
        username = credentials[0];
        password = credentials[1];

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));
        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithLockedOutUser() throws IOException {
        String[] credentials = readCredentialsFromFile(3);  // 3rd line is locked_out_user
        username = credentials[0];
        password = credentials[1];

        _loginPage.loginProcess(username, password);

        errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assertions.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed (Epic sadface: Sorry, this user has been locked out.)");
    }

    @Test
    public void loginWithProblemUser() throws IOException {
        String[] credentials = readCredentialsFromFile(4);  // 4th line is problem_user
        username = credentials[0];
        password = credentials[1];

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));
        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithPerformanceGlitchUser() throws IOException {
        String[] credentials = readCredentialsFromFile(5);  // 5th line is performance_glitch_user
        username = credentials[0];
        password = credentials[1];

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));
        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithErrorUser() throws IOException {
        String[] credentials = readCredentialsFromFile(6);  // 6th line is error_user
        username = credentials[0];
        password = credentials[1];

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));
        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithVisualUser() throws IOException {
        String[] credentials = readCredentialsFromFile(7);  // 7th line is visual_user
        username = credentials[0];
        password = credentials[1];

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));
        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
