package hellocucumber.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.webcompere.lightweightconfig.ConfigLoader;

public class Driver {

    private static final Logger LOG = LoggerFactory.getLogger(Driver.class);
    private static final WebdriverConfigProperties webdriverProps =
            ConfigLoader.loadYmlConfigFromResource(
                    "config/webdriver.yml", WebdriverConfigProperties.class);

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

        // Use DWP WebDriverConfig
//        WebDriverConfig.setTargetUrl(webDriverProps.getUrl());
//        WebDriverConfig.setSeleniumHubUrl(webDriverProps.getSeleniumHubUrl());
//        WebDriverConfig.setBrowser(webDriverProps.getBrowser());
//        WebDriverConfig.setHeadless(webDriverProps.isHeadless());
//        WebDriverConfig.setRemote(webDriverProps.isRemote());
//        WebDriverConfig.setDownloadDefaultDir(webDriverProps.getFileDownloadPath());
//        if (WebDriverManager.getDriver() == null) {
//            WebDriverManager.openBrowser();
//        }
//        WaitActions.setPageLoadTimeout(webDriverProps.getWaitTime());
//        WaitActions.setImplicitWaitTime(webDriverProps.getWaitTime());

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
