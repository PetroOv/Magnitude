package utils.extensions;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.HttpMethod;

import java.lang.reflect.Method;
import java.util.Map;
import utils.interfaces.BrowserFullPageScreenshot;

public class MobileChromeDriver extends ChromeDriver implements BrowserFullPageScreenshot {

    public MobileChromeDriver() throws Exception {
        this(new DesiredCapabilities());
    }

    public MobileChromeDriver(Capabilities capabilities) throws Exception {
        this(ChromeDriverService.createDefaultService(), capabilities);
    }

    public MobileChromeDriver(ChromeDriverService service, Capabilities capabilities) throws Exception {
        super(service, capabilities);
        CommandInfo cmd = new CommandInfo("/session/:sessionId/chromium/send_command_and_get_result", HttpMethod.POST);
        Method defineCommand = HttpCommandExecutor.class.getDeclaredMethod("defineCommand", String.class, CommandInfo.class);
        defineCommand.setAccessible(true);
        defineCommand.invoke(super.getCommandExecutor(), "sendCommand", cmd);
    }

    public Object sendCommand(String cmd, Object params) {
        return execute("sendCommand", ImmutableMap.of("cmd", cmd, "params", params)).getValue();
    }

    public Object sendEvaluate(String script) {
        Object response = sendCommand("Runtime.evaluate", ImmutableMap.of("returnByValue", true, "expression", script));
        Object result = ((Map<String, ?>) response).get("result");
        return ((Map<String, ?>) result).get("value");
    }
}

