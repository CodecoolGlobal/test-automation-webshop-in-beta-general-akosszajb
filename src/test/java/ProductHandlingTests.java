import POM.loginPage;
import POM.shopPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductHandlingTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private loginPage loginPage;
    private shopPage shopPage;


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");

        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        loginPage = new loginPage(driver);
        shopPage = new shopPage(driver, wait);
        loginPage.loginProcess("standard_user", "secret_sauce");

    }

    @Test
    public void testCheckProductsOwnPage() {
        String itemName = shopPage.getItemNameByIndex(1);
        shopPage.viewItemDetailsByIndex(1);

        Assertions.assertTrue(itemName.equals(shopPage.getItemNameOnDetailsPage()));
    }

    @Test
    public void testAllProductsOwnPage() {
        for (WebElement item : shopPage.getAllInventoryItems()) {
            
            String itemName = shopPage.getItemNameByIndex(shopPage.getAllInventoryItems().indexOf(item));
            
            shopPage.viewItemDetailsByIndex(1);

            Assertions.assertTrue(itemName.equals(shopPage.getItemNameOnDetailsPage()));
        }
    }
    
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
