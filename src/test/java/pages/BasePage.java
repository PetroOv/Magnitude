package pages;

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

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class BasePage implements SaveScreenshot {

    protected WebDriver driver;
    Waiter waiter;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        waiter = new Waiter(driver);
        PageFactory.initElements(driver, this);
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
    public BasePage open(String url) {
        driver.get(url);
        return this;
    }

    public void waitForAvailability(int seconds){
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
        if(DriverConfiguration.getDriverName(driver).equals(BrowserType.SAFARI)){
            waiter.pauseFor1Second();
            waiter.pauseFor1Second();
            waiter.pauseFor1Second();
        }
    }

    public Object switchToSecondWindow() {
        waiter.waitFor(numberOfWindowsToBe(2));
        String startedWindow = driver.getWindowHandle();
        driver.getWindowHandles().forEach(window->{
            if (!startedWindow.equals(window))
                driver.switchTo().window(window);
        });
        return this;
    }
}

