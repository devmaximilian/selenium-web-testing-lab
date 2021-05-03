import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchTest {
    private final String searchQuery = "Software Testing";

    @Test
    public void testSearch() throws Exception {
        ChromeDriver driver = new ChromeDriver(
                new ChromeOptions()
                        .setHeadless(Environment.shouldRunHeadless())
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-dev-shm-usage")
        );
        driver.manage().window().setSize(new Dimension(1200, 800));

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
            // Take a screenshot of the test result.
            Files.write(Path.of("./search-test.png"), driver.getScreenshotAs(OutputType.BYTES));

            // Clean up
            driver.close();
        }
    }

}
