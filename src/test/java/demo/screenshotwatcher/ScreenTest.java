package demo.screenshotwatcher;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.bogdanlivadariu.screenshotwatcher.ScreenshotWatcher;
import com.github.bogdanlivadariu.screenshotwatcher.models.BaseScreenshotModel;
import com.github.bogdanlivadariu.screenshotwatcher.models.response.CompareScreenshotsResponse;

public class ScreenTest {

    @Test
    public void blinkTest() {
        // Provide the URL where the tool runs
        // In this example you could rely on the heroku instance
        String baseURL = "https://selenium-screenshot-watcher.herokuapp.com/";
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        String testName = "heroku sample test - 1";
        String testBrowser = "firefox";
        String description = "running screenshot-watcher-sample on travis";

        // Initialize the tool
        ScreenshotWatcher watcher = new ScreenshotWatcher(driver, baseURL);

        // Takes a screenshot
        BaseScreenshotModel scrTaken = watcher.blink(testName, testBrowser, description);

        // Compares the screenshot
        CompareScreenshotsResponse compareResponse = watcher.compare(scrTaken);

        // assert if the images are the same, if not, they visit the review link
        assertTrue(compareResponse.getReviewLink(), compareResponse.isSameImage());
    }
}
