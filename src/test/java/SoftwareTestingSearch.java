import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SoftwareTestingSearch {
    private final String searchQuery = "Software Testing";

    @Test
    public void testSearch() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://google.com");

        // Search for ”Software Testing”
        WebElement searchElement = driver.findElementByName("q");
        searchElement.sendKeys(searchQuery);
        searchElement.submit();

        // Verify that the title reflects the search query
        String title = driver.getTitle();
        assert(title.contains(searchQuery));

        // Clean up
        driver.close();
    }

}
