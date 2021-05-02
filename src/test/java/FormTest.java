import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormTest {

    @Test
    public void testForm() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.actitime.com");

        // Navigate by pressing the ”Try Free” button
        WebElement button = driver.findElementByLinkText("Try Free");
        button.click();

        // Form elements
        WebElement firstNameInput = driver.findElementByName("firstName");
        WebElement lastNameInput = driver.findElementByName("lastName");
        WebElement emailAddressInput = driver.findElementByName("emailAddress");
        WebElement companyInput = driver.findElementByName("company");

        // Enter test data
        firstNameInput.sendKeys("Foo");
        lastNameInput.sendKeys("Bar");
        emailAddressInput.sendKeys("foo@bar.local");
        companyInput.sendKeys("Foo Bar");

        // Clean up
        driver.close();
    }

}
