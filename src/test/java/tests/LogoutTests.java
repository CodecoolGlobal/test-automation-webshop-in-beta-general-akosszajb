package tests;

import POM.loginPage;
import POM.navbar;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private loginPage login;
    private navbar navbar;

    @Before
    public void setUp(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        //
        login = new loginPage(driver);
        navbar = new navbar(driver,wait);
    }
    

    @After
    public void tearDown() {
        driver.quit();
    }
}
