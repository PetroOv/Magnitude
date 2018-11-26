package utils.interfaces;

import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface DriverConfiguration {
    int WIDTH = 1680;
    int HEIGHT = 1050;
    WebDriver useRemoteDriver(String browserName);

    WebDriver useRemoteDriver(DesiredCapabilities desiredCapabilities);

    WebDriver useLocalDriver(String browserName);

    WebDriver useLocalDriver(DesiredCapabilities desiredCapabilities);

    default void checkDriverInitialization(WebDriver driver) {
        if (driver == null) {
            Assert.fail("Some problem with driver initialization. Check Selenium Grid");
        }
    }

    default void setDriverSize(WebDriver driver, int width, int height) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }
    static String getDriverName(WebDriver driver){
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        return  browserName;
    }

    default void quitDriver(WebDriver driver){
        if (driver != null)
            driver.quit();
    }
}
