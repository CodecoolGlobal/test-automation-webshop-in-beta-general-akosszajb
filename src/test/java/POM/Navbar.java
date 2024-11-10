package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navbar {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement reactMenuButton;

    @FindBy(css = "nav[class='bm-item-list']")
    private WebElement navSideBarOpen;


    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public Navbar(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickReactMenuButton() {
        wait.until(ExpectedConditions.visibilityOf(reactMenuButton));
        reactMenuButton.click();
    }


    public void clickLogoutLink() {
        wait.until(ExpectedConditions.visibilityOf(navSideBarOpen));
        logoutLink.click();
    }

}
