package utils.extensions;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import utils.interfaces.SaveScreenshot;
import utils.interfaces.UsingDriver;

public class ScreenShotOnFailureRuleWOJs extends TestWatcher implements SaveScreenshot, UsingDriver {

    private WebDriver driver;

    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    @Override
    protected void failed(Throwable e, Description description) {
        if (driver != null) {
            attachImage("Error screenshot", getPageScreenShot(driver));
        }
    }

    @Override
    protected void succeeded(Description description) {

    }

    @Override
    protected void finished(Description description) {
        closeDriver();
    }

    private void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}