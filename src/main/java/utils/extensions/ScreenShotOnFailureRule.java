package utils.extensions;

import org.junit.Assert;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Date;
import utils.interfaces.SaveScreenshot;
import utils.interfaces.UsingDriver;
import utils.interfaces.WriteLogs;

import static utils.configurations.EnvironmentConfiguration.isProduction;

public class ScreenShotOnFailureRule extends TestWatcher implements SaveScreenshot, WriteLogs, UsingDriver {

    private boolean enableLogs = true;
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
        if (enableLogs) {
            try {
                Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
                String browserName = cap.getBrowserName();

                if (browserName.equals(BrowserType.CHROME) || browserName.equals("opera"))
                    writeLogs();
            } catch (NullPointerException ignored) {
            }
        }
    }

    public void disableLogsOnLocal() {
        enableLogs = isProduction();
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

    @Override
    public LogEntries getLogs() {
        return driver.manage().logs().get(LogType.BROWSER);
    }


    public void writeLogs() {
        LogEntries logEntries = getLogs();
        StringBuilder result = new StringBuilder();
        for (LogEntry entry : logEntries) {
            if (!entry.getMessage().contains("booking.com"))
                result.append(new Date(entry.getTimestamp())).append(" ").append(entry.getMessage()).append("\n");
        }
        if (!result.toString().equals("")) {
            logs(result.toString());
            System.out.println(result.toString());
            Assert.fail("Js errors detected");
        }
    }

}