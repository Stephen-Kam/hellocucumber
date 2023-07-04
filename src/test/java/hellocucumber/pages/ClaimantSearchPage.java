package hellocucumber.pages;

import hellocucumber.driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClaimantSearchPage extends BasePage {

    public String url = "/";

    public String heading = "Search for a claimant";

    @FindBy(id = "ninoOrEmail")
    private WebElement claimantSearchField;

    public ClaimantSearchPage(Driver driver) {
        super(driver);
        PageFactory.initElements(driver.getDriver(), this);
    }


    public void searchClaimant(String search) {
        claimantSearchField.sendKeys(search);
    }
}
