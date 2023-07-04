package hellocucumber.pages;

import hellocucumber.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BasePage {

    @FindBy(css = "h1")
    private WebElement heading;

    public final WebDriver driver;

    private final String baseUrl = "http://localhost:3001";

    public BasePage(Driver driver) {
        this.driver = driver.getDriver();
    }

    public void navigateTo(String url) {
        driver.navigate().to(baseUrl + url);
    }

    public void on(String expectedHeading, String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains(url));
        String actualHeading = heading.getText();
        assertThat(actualHeading, is(equalTo(expectedHeading)));
    }

    public void submit() {
        driver.switchTo().activeElement().submit();
    }
}
