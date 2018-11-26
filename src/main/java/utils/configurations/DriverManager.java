package utils.configurations;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.interfaces.DriverCapabilities;
import utils.interfaces.DriverConfiguration;
import utils.interfaces.EdgeConfiguration;

import java.net.URL;
import java.util.Objects;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static utils.configurations.EnvironmentConfiguration.GRID_URL;

public class DriverManager implements DriverCapabilities, EdgeConfiguration, DriverConfiguration {
    protected WebDriver driver;
    private String browserName;
    private DriverLocation location;

    public DriverManager() {
        this(CHROME, DriverLocation.LOCAL);
    }

    public DriverManager(DriverManagerType driverManagerType, DriverLocation driverLocation) {
        WebDriverManager.getInstance(driverManagerType).setup();
        this.browserName = driverManagerType.name();
        location = driverLocation;
        checkBrowserName();
    }

    public static void openPage(WebDriver driver, String page) {
        if (driver != null) driver.get(navigateToPage(page));
    }

    @Step
    private static String navigateToPage(String page) {
        return page;
    }

    public void quitDriver(WebDriver driver) {
        if (driver != null)
            driver.quit();
    }

    private void checkBrowserName() {
        if (browserName == null | Objects.requireNonNull(browserName).isEmpty())
            this.browserName = BrowserType.CHROME;
    }

    public WebDriver initDriver() {
        checkBrowserName();
        if(location.equals(DriverLocation.LOCAL)) {
            driver = useLocalDriver(browserName);
        }else {
            driver = useRemoteDriver(browserName);
        }
        checkDriverInitialization(driver);
        setDriverSize(driver, WIDTH, HEIGHT);
        return driver;
    }

    public WebDriver initDriver(DesiredCapabilities capabilities) {
        if(location.equals(DriverLocation.LOCAL)) {
            driver = useLocalDriver(browserName);
        } else {
            driver = useRemoteDriver(browserName);
        }
        checkDriverInitialization(driver);
        setDriverSize(driver, WIDTH, HEIGHT);
        return driver;
    }

    @Step
    @Override
    public WebDriver useRemoteDriver(String browserName) {
        try {
            return new RemoteWebDriver(new URL(GRID_URL), getCapabilities(browserName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WebDriver useRemoteDriver(DesiredCapabilities desiredCapabilities) {
        try {
            return new RemoteWebDriver(new URL(GRID_URL), desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WebDriver useLocalDriver(String browserName) {
        try {
            return driver = new ChromeDriver(getCapabilities(browserName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WebDriver useLocalDriver(DesiredCapabilities desiredCapabilities) {
        try {
            return driver = new ChromeDriver(desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void privateWindowForEdge(String browserName) {
        if (browserName.equals("edge")) {
            forEdge(driver);
        }
    }
}
