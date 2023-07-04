package hellocucumber.pages;

import hellocucumber.driver.Driver;
import org.openqa.selenium.support.PageFactory;

public class SendInvitationPage extends BasePage {

    public String url = "/pip-send-invitation";

    public String header = "Send an invitation to complete PIP2 online";

    public SendInvitationPage(Driver driver) {
        super(driver);
        PageFactory.initElements(driver.getDriver(), this);
    }
}
