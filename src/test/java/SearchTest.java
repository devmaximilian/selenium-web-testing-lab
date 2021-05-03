import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchTest {
    private final String searchQuery = "Software Testing";

    @Test
    public void testSearch() {
        ChromeDriver driver = new ChromeDriver(
                new ChromeOptions()
                        .setHeadless(Environment.shouldRunHeadless())
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-dev-shm-usage")
        );

        try {
            driver.get("https://google.com");

            // Search for ”Software Testing”
            WebElement searchElement = driver.findElementByName("q");
            searchElement.sendKeys(searchQuery);
            searchElement.submit();

            // Verify that the title reflects the search query
            String title = driver.getTitle();
            assert (title.contains(searchQuery));
        } finally {
            // Clean up
            driver.close();
        }
    }

}
