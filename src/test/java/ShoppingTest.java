import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingTest {
    private final String searchQuery = "praktisk mjukvarutestning";

    @Test
    public void testShopping() throws Exception {
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        try {
            driver.get("https://www.adlibris.com/se");

            // Search for ”praktisk mjukvarutestning”
            WebElement searchElement = driver.findElementByName("q");
            searchElement.sendKeys(searchQuery);
            searchElement.submit();

            // Navigate to the product page...
            WebElement productElement = driver.findElementById("search")
                                              .findElement(By.className("search-result__product__name"));
            productElement.click();

            // ... and add product to cart.
            WebElement addToCartElement = driver.findElementByClassName("product__add-to-cart");
            addToCartElement.click();

            // Click on view cart.
            WebElement cartElement = driver.findElementByClassName("page-header__toggler--cart");
            cartElement.click();

            // Wait for the product to be added to the cart...
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        new ByChained(
                                By.className("mini-cart"),
                                By.linkText("Till kassan")
                        )
                    )
            );

            // ... and proceed to checkout.
            WebElement checkoutElement = driver.findElementByClassName("mini-cart")
                                               .findElement(By.linkText("Till kassan"));
            checkoutElement.click();

            // Click on modify cart.
            WebElement modifyCartElement = driver.findElementByClassName("cart__change-cart-link");
            modifyCartElement.click();

            // Wait for the product cart to be modifiable...
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("product-cart-view__delete")
                    )
            );

            // ... to remove item from cart.
            WebElement removeItemElement = driver.findElementByClassName("product-cart-view__delete");
            removeItemElement.click();

            // Wait for the product cart to update.
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("cart__status-message__message")
                    )
            );

            // Get the cart status.
            WebElement cartStatusElement = driver.findElementByClassName("cart__status-message__message");
            String cartStatusText = cartStatusElement.getText();

            // ... to verify that the cart is empty.
            assert(cartStatusText.contains("Kundvagnen är tom"));
        } finally {
            // Clean up
            driver.close();
        }
    }

}
