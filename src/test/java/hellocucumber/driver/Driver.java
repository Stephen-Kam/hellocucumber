package hellocucumber.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {

    private static final Logger LOG = LoggerFactory.getLogger(Driver.class);

    public WebDriver driver;

    public void destroyDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        // Get the system property named 'browser', if empty, use 'firefox'
        String browser = System.getProperty("browser", "firefox");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                LOG.warn("Invalid browser selection, defaulting to Firefox");
                driver = new FirefoxDriver(firefoxOptions);
                break;
        }
        this.driver = driver;
    }
}
