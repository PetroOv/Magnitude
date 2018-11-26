package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.data.Dates.END_DAY_FORMATTED_CLICKUNDER;
import static utils.data.Dates.START_DAY_FORMATTED_CLICKUNDER;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClickUnderPage extends BasePage {
    @FindBy(how = How.CSS, using = ".js-header__logo ")
    private WebElement logo;

    public ClickUnderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step
    public ClickUnderPage waitForBookingComPage() {
        waiter.waitFor(ExpectedConditions.visibilityOf(logo), 30);
        return this;
    }

    @Step
    public ClickUnderPage checkRedirectDates() {
        String URL = driver.getCurrentUrl();
        assertThat("AffiliateId parameter isn't sent", URL.contains("aid=1192690"));
        assertThat("Check-in or check-out parameter isn't sent",
                URL.contains(String.format("checkin=%s&checkout=%s",
                        START_DAY_FORMATTED_CLICKUNDER, END_DAY_FORMATTED_CLICKUNDER)));
        return this;
    }


}
