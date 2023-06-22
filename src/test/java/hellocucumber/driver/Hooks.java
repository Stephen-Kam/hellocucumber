package hellocucumber.driver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger LOG = LoggerFactory.getLogger(Driver.class);

    private final Driver driver;

    public Hooks(Driver driver) {
        this.driver = driver;
    }

    @Before()
    public void initDriver() {
        driver.initDriver();
    }

    @BeforeEach
    public void clearCookies() {
        driver.getDriver().manage().deleteAllCookies();
    }

    @After()
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
    }

    @After("@ui")
    public void quitDriver() {
        driver.destroyDriver();
    }
}
