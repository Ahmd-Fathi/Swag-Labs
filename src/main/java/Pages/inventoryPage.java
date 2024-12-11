package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

public class inventoryPage extends AbstractComponent {
    WebDriver driver;

    public inventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By productWordAssert = By.cssSelector(".title");
    private By iTemsNames = By.cssSelector("div[data-test=\"inventory-item-name\"]");
    private By AddToCartButonns = By.xpath("//button[@class=\"btn btn_primary btn_small btn_inventory \"]");
    private  By AddToCartLinkText=By.cssSelector(".shopping_cart_link");
    public void selectThreeItems() throws InterruptedException {

        // Get all product elements on the page
        List<WebElement> productElements = driver.findElements(iTemsNames);

        // Stream to select and click on each of the three products
        Stream.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Onesie").forEach(productName -> {

            // Find the specific product by name
            WebElement specificProduct = productElements.stream()
                    .filter(product -> product.getText().equalsIgnoreCase(productName))
                    .findFirst().orElse(null);

            // If the product is found, print its name and click the "Add to Cart" button
            if (specificProduct != null) {
                System.out.println(specificProduct.getText()); // Print the product name
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.elementToBeClickable(AddToCartButonns));
                    specificProduct.findElement(AddToCartButonns).click(); // Click "Add to Cart"
                } catch (Exception e) {
                    System.out.println("Error clicking on: " + productName);
                }
            } else {
                System.out.println("Product not found: " + productName);
            }
        }); // This is the correct closing of forEach

    }

    public boolean productWordAssertAfterReachedInventoryPage() {
        return driver.findElement(productWordAssert).isDisplayed();
    }
public  CartPage ClickAddToCartLinkTextBtn()
{

    driver.findElement(AddToCartLinkText).click();
    return  new CartPage(driver);


}
}
