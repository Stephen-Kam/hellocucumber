package hellocucumber;

import hellocucumber.driver.Driver;
import hellocucumber.pages.ClaimantSearchPage;
import hellocucumber.pages.SendInvitationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsItFriday {

    static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}

public class StepDefinitions {

    private String today;
    private String actualAnswer;

    private final Driver driver;

    private final ClaimantSearchPage claimantSearchPage;
    private final SendInvitationPage sendInvitationPage;

    public StepDefinitions(Driver driver,
                           ClaimantSearchPage claimantSearchPage,
                           SendInvitationPage sendInvitationPage) {
        this.driver = driver;
        this.claimantSearchPage = claimantSearchPage;
        this.sendInvitationPage = sendInvitationPage;
    }

    @Given("today is {string}")
    public void today_is(String today) {
        this.today = today;
    }

    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        driver.getDriver().get("https://www.google.com");
        // Google's cookie consent page appears and needs accepting
        WebElement element = driver.getDriver().findElement(By.id("L2AGLb"));
        element.click();
    }

    @Given("A user is on the claimant submission screen")
    public void a_user_is_on_the_claimant_submission_screen() {
        claimantSearchPage.navigateTo(claimantSearchPage.url);
        claimantSearchPage.on(claimantSearchPage.heading, claimantSearchPage.url);
    }

    @When("A nino is provided")
    public void a_nino_is_provided() {
        claimantSearchPage.searchClaimant("AA123456D");
        claimantSearchPage.submit();
    }

    @When("I search for {string}")
    public void i_search_for(String query) {
        WebElement element = driver.getDriver().findElement(By.name("q"));
        //Enter something to search for
        element.sendKeys(query);
        //Now submit the form. Webdriver will find the form for us from the element
        element.submit();
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Then("the page title should start with {string}")
    public void the_page_title_should_start_with(String titleStartsWith) {
        // Google's search is rendered dynamically with Javascript
        // Wait for the page to load timeout after 10 seconds
        new WebDriverWait(driver.getDriver(), Duration.of(10L, ChronoUnit.SECONDS)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(titleStartsWith);
            }
        });
    }

    @Then("They will be on the next page")
    public void they_will_be_on_the_next_page() {
        sendInvitationPage.on(sendInvitationPage.header, sendInvitationPage.url);
    }
}
