package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShopPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ShopPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(xpath = "//option[@value='az']")
    private WebElement nameAToZOption;

    @FindBy(xpath = "//option[@value='za']")
    private WebElement nameZToAOption;

    @FindBy(xpath = "//option[@value='lohi']")
    private WebElement priceLowToHighOption;

    @FindBy(xpath = "//option[@value='hilo']")
    private WebElement priceHighToLowOption;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement shoppingCartButton;

    @FindBy(css = "a.shopping_cart_link span.shopping_cart_badge")
    private WebElement shoppingCartIconNumbers;

    @FindBy(id = "add-to-cart")
    private WebElement itemDetailsPageAddToCartButton;

    @FindBy(xpath = "//div[@data-test = 'inventory-item-name']")
    private WebElement itemDetailsNameText;

    @FindBy(css = "html > body > div > div > div > div:nth-of-type(2) > div > div > div > div:nth-of-type(1) > div:nth-of-type(2) > div:nth-of-type(1) > a > div")
    private WebElement firstItemName;

    @FindBy(xpath = "//span[@data-test='title' and text()='Products']")
    private WebElement productsTitle;

    @FindBy(xpath = "//div[@data-test = 'inventory-item-desc']")
    private WebElement itemDetailsDescriptionText;

    public String getFirstItemName() {
        return firstItemName.getText();
    }

    public WebElement getProductsTitle() {
        return productsTitle;
    }

    public void selectNameAToZ() {
        wait.until(ExpectedConditions.visibilityOf(nameAToZOption)).click();
    }

    public void selectNameZToA() {
        wait.until(ExpectedConditions.visibilityOf(nameZToAOption)).click();
    }

    public void selectPriceLowToHigh() {
        wait.until(ExpectedConditions.visibilityOf(priceLowToHighOption)).click();
    }

    public void selectPriceHighToLow() {
        wait.until(ExpectedConditions.visibilityOf(priceHighToLowOption)).click();
    }

    public WebElement getAddToCartButtonByIndex(int index) {
        return wait.until(ExpectedConditions.elementToBeClickable((By.xpath("(//button[contains(@id, 'add-to-cart')])[" + index + "]"))));
    }

    public WebElement getRemoveFromCartButtonByIndex(int index) {
        return wait.until(ExpectedConditions.elementToBeClickable((By.xpath("(//button[contains(@id, 'remove')])[" + index + "]"))));
    }

    public void addItemToCartByIndex(int index) {
        WebElement addToCartButton = getAddToCartButtonByIndex(index);
        addToCartButton.click();
    }

    public void removeItemFromCartByIndex(int index) {
        if (isItemInCartByIndex(index)) {
            WebElement removeButton = getRemoveFromCartButtonByIndex(index);
            removeButton.click();
        } else {
            throw new IllegalStateException("Item at index " + index + " is not in the cart, cannot remove.");
        }
    }

    public String getAddOrRemoveStateOfButtonByIndex(int index) {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//button[contains(@class, 'btn_inventory')])[" + index + "]")));
        return button.getText();
    }

    public boolean isItemInCartByIndex(int index) {
        String buttonText = getAddOrRemoveStateOfButtonByIndex(index);
        return buttonText.equalsIgnoreCase("Remove");
    }

    public WebElement getItemByIndex(int index) {
        By locator = By.xpath("(//div[contains(@class, 'inventory_item_name')])[" + index + "]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getItemNameByIndex(int index) {
        return getItemByIndex(index).getText();
    }

    public void viewItemDetailsByIndex(int index) {
        WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class, 'inventory_item_name')])[" + index + "]")));
        itemElement.click();

    }

    public void clickAddToCartButtonOnItemDetailsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(itemDetailsPageAddToCartButton)).click();
    }

    public void clickBackToProductsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backToProductsButton)).click();
    }

    public String getNumberOfItemsInCart() {
        try {
            WebElement cartIconElement = wait.until(ExpectedConditions.visibilityOf(shoppingCartIconNumbers));
            return cartIconElement.getText();

        } catch (TimeoutException e) {
            return "0";
        }
    }

    public void clickShoppingCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButton)).click();
    }

    public String getItemNameOnDetailsPage() {
        return wait.until(ExpectedConditions.visibilityOf(itemDetailsNameText)).getText();
    }

    public WebElement getItemDescriptionOnDetailsPage() {
        return wait.until(ExpectedConditions.visibilityOf(itemDetailsDescriptionText));
    }

    public List<WebElement> getAllInventoryItems() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(inventoryItems));
    }

}
