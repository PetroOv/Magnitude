package utils.configurations;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import utils.extensions.MobileChromeDriver;
import utils.extensions.MobileRemoteDriver;
import utils.extensions.ScreenShotOnFailureRule;
import utils.extensions.Waiter;
import utils.interfaces.DriverCapabilities;
import utils.interfaces.DriverConfiguration;
import utils.interfaces.UsingDriver;

import static utils.configurations.EnvironmentConfiguration.ENVIRONMENT;
import static utils.configurations.EnvironmentConfiguration.GRID_URL;
import static utils.configurations.EnvironmentConfiguration.useSafari;

public class MobileDriverManager implements DriverCapabilities, DriverConfiguration {
    public static String hotelURL;
    public static String mainPageURL;
    public static String hotelListURL;

    @Parameterized.Parameter
    public String browserName;

    @Rule
    public TestWatcher screenshotOnFailureRule = new ScreenShotOnFailureRule();

    protected WebDriver driver;
    protected String testDay;
    protected String endDayFormatted;
    protected String startDayFormatted;
    protected Calendar startDay;
    protected Calendar endDay;
    Waiter waiter;

    @Parameterized.Parameters(name = "Model - {0}")
    public static List<Object[]> getData() {
//        return Arrays.asList(new Object[][]{new Object[]{"Nexus 5"}});
        if (!useSafari())
            return Arrays.asList(new Object[]{"Nexus 5"}, new Object[]{"iPhone 5"});
        else
            return Arrays.asList();
    }

    @Override
    public DesiredCapabilities chromeCapabilities() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", browserName);
        ChromeOptions chromeOptions = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, browserErrorsLogging());
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }

    public void initURLs() {
        hotelURL = String.format("http://m.booked.%s/hotel/hilton-kyiv-kiev-409285", ENVIRONMENT);
        mainPageURL = String.format("http://m.booked.%s/", ENVIRONMENT);
        hotelListURL = String.format("http://m.booked.%s/hotels/italy/rome", ENVIRONMENT);
    }

    private void setDate() {
        startDay = Calendar.getInstance();
        endDay = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        testDay = simpleDateFormat.format(startDay.getTime());
        startDay.add(Calendar.DATE, 1);
        endDay.add(Calendar.DATE, 2);
        startDayFormatted = simpleDateFormat.format(startDay.getTime());
        endDayFormatted = simpleDateFormat.format(endDay.getTime());
    }

    @Before
    public void setUp() {
        initURLs();
        setDate();
        driver = useRemoteDriver(browserName);
//        driver = useLocalDriver(browserName);
        setDefaultDriverWindowSize();
        waiter = new Waiter(driver);
        ((ScreenShotOnFailureRule) screenshotOnFailureRule).disableLogsOnLocal();
        ((UsingDriver) screenshotOnFailureRule).setDriver(driver);
    }

    public WebDriver useRemoteDriver(String browserName) {
        try {
            return new MobileRemoteDriver(new URL(GRID_URL), getCapabilities(browserName));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public WebDriver useRemoteDriver(DesiredCapabilities desiredCapabilities) {
        return null;
    }

    public WebDriver useLocalDriver(String browserName) {
        try {
            return driver = new MobileChromeDriver(getCapabilities(browserName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WebDriver useLocalDriver(DesiredCapabilities desiredCapabilities) {
        return null;
    }

    public void setDefaultDriverWindowSize() {
        if (browserName.equals("Nexus 5")) {
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(360, 640));
        } else {
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(320, 568));
        }
    }
}
