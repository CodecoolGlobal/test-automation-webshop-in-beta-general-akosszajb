package tests;

import POM.ShopPage;
import POM.LoginPage;
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

public class OrderedListTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ShopPage shopPage;


    @BeforeEach
    public void setup() {
        Dotenv dotenv = Dotenv.load();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        loginPage = new LoginPage(driver, wait);
        shopPage = new ShopPage(driver, wait);

        String url = dotenv.get("URL");
        driver.get(url);

        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.loginProcess(username,password);
    }

    @Test
    public void orderedListHighToLow() {
        shopPage.selectPriceHighToLow();

        String expected = "Sauce Labs Fleece Jacket";
        String actual = shopPage.getFirstItemName();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void orderedListLowToHigh() {
        shopPage.selectPriceLowToHigh();

        String expected = "Sauce Labs Onesie";
        String actual = shopPage.getFirstItemName();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void orderedListAToZ() {
        shopPage.selectNameAToZ();

        String expected = "Sauce Labs Backpack";
        String actual = shopPage.getFirstItemName();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void orderedListZToA() {
        shopPage.selectNameZToA();

        String expected = "Test.allTheThings() T-Shirt (Red)";
        String actual = shopPage.getFirstItemName();

        Assertions.assertEquals(expected, actual);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
