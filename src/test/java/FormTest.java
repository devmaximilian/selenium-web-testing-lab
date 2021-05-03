import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.Files;
import java.nio.file.Path;

public class FormTest {

    @Test
    public void testForm() throws Exception {
        ChromeDriver driver = new ChromeDriver(
                new ChromeOptions()
                        .setHeadless(Environment.shouldRunHeadless())
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-dev-shm-usage")
        );
        driver.manage().window().setSize(new Dimension(1200, 800));

        try {
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
        } catch (Exception e) {
            // Take a screenshot of the failure and rethrow the exception.
            Files.write(Path.of("./form-test.png"), driver.getScreenshotAs(OutputType.BYTES));
            throw e;
        } finally {
            // Clean up
            driver.close();
        }
    }

}
