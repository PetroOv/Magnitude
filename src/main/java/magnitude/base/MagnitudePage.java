package magnitude.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;
import utils.extensions.Waiter;
import utils.interfaces.BrowserFullPageScreenshot;
import utils.interfaces.DriverConfiguration;
import utils.interfaces.SaveScreenshot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public abstract class MagnitudePage implements SaveScreenshot, PageLoader {

    protected WebDriver driver;
    protected Waiter waiter;
    protected JavascriptExecutor js;
    private ActionWorker actionWorker;

    public MagnitudePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        waiter = new Waiter(driver);
        actionWorker = new ActionWorker(driver);
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        actionWorker.click(element);
    }

    public void click(By locator) {
        actionWorker.click(locator);
    }

    public String getText(WebElement element) {
        return actionWorker.getText(element);
    }

    public String getText(By locator) {
        return actionWorker.getText(locator);
    }

    public void sendKeys(WebElement element, CharSequence... charSequences) {
        actionWorker.sendKeys(element, charSequences);
    }

    public void sendKeys(By locator, CharSequence... charSequences) {
        actionWorker.sendKeys(locator, charSequences);
    }

    public boolean isElementPresent(By element) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean result = driver.findElements(element).size() > 0;
        int timeout = 4;
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        return result;
    }

    public BufferedImage getFullPageScreenShot() {
        return getFullPageScreenShot(driver);
    }

    @Step
    public MagnitudePage open(String url) {
        driver.get(url);
        return this;
    }

    public void waitForAvailability(int seconds) {
        waiter.waitForAvailability(seconds);
    }

    public BufferedImage getMobileFullPageScreenShot(WebDriver driver) {
        File file = null;
        try {
            return ((BrowserFullPageScreenshot) driver).getFullScreenshotAs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BufferedImage(1, 1, 1);
    }

    public void scrollToTheTopOfPage() {
        js.executeScript("window.scrollTo(0,0);");
        waiter.pauseFor1Second();
    }

    public Long getCurrentPositionOnPage() {
        return (Long) js.executeScript("return window.pageYOffset;");
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public BufferedImage getMobileFullPageScreenShot() {
        return getMobileFullPageScreenShot(this.driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void waitOneSecond() {
        waiter.pauseFor1Second();
    }

    public void wait3Seconds() {
        waiter.pauseFor1Second();
        waiter.pauseFor1Second();
        waiter.pauseFor1Second();
        if (DriverConfiguration.getDriverName(driver).equals(BrowserType.SAFARI)) {
            waiter.pauseFor1Second();
            waiter.pauseFor1Second();
            waiter.pauseFor1Second();
        }
    }

    public Object switchToSecondWindow() {
        waiter.waitFor(numberOfWindowsToBe(2));
        String startedWindow = driver.getWindowHandle();
        driver.getWindowHandles().forEach(window -> {
            if (!startedWindow.equals(window))
                driver.switchTo().window(window);
        });
        return this;
    }

    @Override
    public void load() {
        driver.get("https://github.com/SeleniumHQ/selenium/issues/new");
    }

    @Override
    public void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue("Not on the issue entry page: " + url, url.endsWith("/new"));
    }
}
