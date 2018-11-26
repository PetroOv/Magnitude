package utils.interfaces;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public interface DriverCapabilities {
    default DesiredCapabilities getCapabilities(String browserName) {
        DesiredCapabilities capabilities;
        switch (browserName) {
            case "opera": {
                capabilities = operaCapabilities();
                break;
            }
            case "internet explorer": {
                capabilities = ieCapabilities();
                break;
            }
            case "chrome": {
                capabilities = chromeCapabilities();
                break;
            }
            case "firefox": {
                capabilities = firefoxCapabilities();
                break;
            }
            case "edge": {
                capabilities = edgeCapabilities();
                break;
            }
            case "safari": {
                capabilities = safariCapabilities();
                break;
            }
            default:
                capabilities = chromeCapabilities();
                break;
        }
        return capabilities;
    }

    default DesiredCapabilities safariCapabilities(){
        DesiredCapabilities capabilities = DesiredCapabilities.safari();
        return capabilities;
    }

    default DesiredCapabilities edgeCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.edge();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, browserErrorsLogging());
        capabilities.setBrowserName(BrowserType.EDGE);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        return capabilities;
    }

    default DesiredCapabilities firefoxCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, browserErrorsLogging());
        return capabilities;
    }

    default DesiredCapabilities chromeCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, browserErrorsLogging());
        return capabilities;
    }

    default DesiredCapabilities operaCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Opera\\52.0.2871.42\\opera.exe");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, browserErrorsLogging());
        return capabilities;
    }

    default DesiredCapabilities ieCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
        capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, browserErrorsLogging());
        return capabilities;
    }

    default LoggingPreferences browserErrorsLogging() {
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.SEVERE);
        return loggingPreferences;
    }
}
