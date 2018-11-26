package utils.extensions;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waiter {
    private static final int DEFAULT_TIME_OUT = 10;
    WebDriver driver;
    ExpectedCondition<Boolean> availabilityExpectation = driver ->
            driver.findElements(By.cssSelector(".booked-progress-city-block")).size() == 0
                    && Boolean.valueOf(((JavascriptExecutor) driver).
                    executeScript("return (window.angular !== undefined) " +
                            "&& (angular.element(document).injector() !== undefined) " +
                            "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)")
                    .toString());

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitFor(final ExpectedCondition condition) {
        getWaiter().until(condition);
    }

    public void waitFor(final ExpectedCondition condition, int seconds) {
        getWaiter().withTimeout(seconds, TimeUnit.SECONDS).until(condition);
    }

    private WebDriverWait getWaiter() {
        return getWaiter(DEFAULT_TIME_OUT);
    }

    private WebDriverWait getWaiter(int seconds) {
        return new WebDriverWait(driver, seconds);
    }

    public void waitForJquery() {
        getWaiter().until((ExpectedCondition<Boolean>) webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return (Boolean) js.executeScript("return jQuery.active == 0");
        });
    }

    @Step
    public void waitForFinishingLoading() {
        waitFor(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".main-loader"), 0), 30);
    }

    public void waitForPageLoaded() {
        getWaiter(30).until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));
    }

    public void pauseFor1Second() {
        int pauseTime = 1000;
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        if(browserName.equals(BrowserType.IE))
            pauseTime = 2000;
        try {
            Thread.sleep(pauseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void waitForAvailability() {
        waitForAvailability(90);
    }
    public void waitForAvailability(int seconds){
        try {
            waitFor(availabilityExpectation,seconds);
        } catch (Throwable error) {
            Assert.fail("Availability > " + seconds + " seconds");
        }
    }
    //other waiters
}
