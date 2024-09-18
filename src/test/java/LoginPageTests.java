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


import java.time.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPageTests {
    private String username;
    private String password;
    private WebDriver driver;
    private WebDriverWait wait;
    private loginPage _loginPage;
    private WebElement productTitle;
    WebElement errorMessage;

    @BeforeEach
    public void setup() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader());
        String chromeDriverPath = reader.readLine();
        reader.close();

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        _loginPage = new loginPage(driver);
    }

    @Test
    public void loginWithStandardUser() throws IOException {
        BufferedReader reader = new BufferedReader(new BufferedReader(new FileReader()));
        password = reader.readLine();
        username = reader.readLine();
        reader.close();

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));

        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithLockedOutUser() throws IOException {

        int passwordLineNumber = 1;
        int usernameLineNumber = 3;

        try (BufferedReader reader = new BufferedReader(new FileReader()) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == passwordLineNumber) {
                    password = line;
                } else if (currentLine == usernameLineNumber) {
                    username = line;
                }
            }
        }

        _loginPage.loginProcess(username, password);

        errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));

        Assertions.assertTrue(errorMessage.isDisplayed(), "Error messagge is not displayed (Epic sadface: Sorry, this user has been locked out.)");
    }

    @Test
    public void loginWithProblemUser() throws IOException {
        int passwordLineNumber = 1;
        int usernameLineNumber = 4;

        try (BufferedReader reader = new BufferedReader(new FileReader()) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == passwordLineNumber) {
                    password = line;
                } else if (currentLine == usernameLineNumber) {
                    username = line;
                }
            }
        }

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));

        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithPerformanceGlitchUser() throws IOException {
        int passwordLineNumber = 1;
        int usernameLineNumber = 5;

        try (BufferedReader reader = new BufferedReader(new FileReader()) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == passwordLineNumber) {
                    password = line;
                } else if (currentLine == usernameLineNumber) {
                    username = line;
                }
            }
        }

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));

        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithErrorUser() throws IOException {
        int passwordLineNumber = 1;
        int usernameLineNumber = 6;

        try (BufferedReader reader = new BufferedReader(new FileReader()) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == passwordLineNumber) {
                    password = line;
                } else if (currentLine == usernameLineNumber) {
                    username = line;
                }
            }
        }

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));

        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @Test
    public void loginWithVisualUser() throws IOException {
        int passwordLineNumber = 1;
        int usernameLineNumber = 7;

        try (BufferedReader reader = new BufferedReader(new FileReader()) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == passwordLineNumber) {
                    password = line;
                } else if (currentLine == usernameLineNumber) {
                    username = line;
                }
            }
        }

        _loginPage.loginProcess(username, password);

        productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' and text()='Products']")));

        Assertions.assertTrue(productTitle.isDisplayed(), "Products title is not displayed");
    }

    @AfterEach
    public void tearDown() {
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
}
