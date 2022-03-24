package base;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class Base {
    public WebDriver driver;
    private final String BASE_URL = "https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform";

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getExecutablePath() {
        String path = System.getenv("SELENIUM_EXECUTABLE_PATH");

        if (path == null) {
            throw new NotFoundException("Please make sure that you have set SELENIUM_EXECUTABLE_PATH environment variable");
        }

        return path;
    }

    private void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", getExecutablePath());
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void init() {
        driver.close();
    }

    @BeforeMethod
    public void beforeSuit() {
        setupSelenium();
        driver.get(BASE_URL);
    }


    @AfterClass
    public void cleanup() {
        driver.quit();
    }
}
