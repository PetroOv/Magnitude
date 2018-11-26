package utils.extensions;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpMethod;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;
import utils.interfaces.BrowserFullPageScreenshot;

public class MobileRemoteDriver extends RemoteWebDriver implements BrowserFullPageScreenshot {

    public MobileRemoteDriver(Capabilities capabilities) throws Exception {
        super(capabilities);
        CommandInfo cmd = new CommandInfo("/session/:sessionId/chromium/send_command_and_get_result", HttpMethod.POST);
        Method defineCommand = HttpCommandExecutor.class.getDeclaredMethod("defineCommand", String.class, CommandInfo.class);
        defineCommand.setAccessible(true);
        defineCommand.invoke(super.getCommandExecutor(), "sendCommand", cmd);
    }

    public MobileRemoteDriver(URL url, Capabilities capabilities) throws Exception {
        super(url, capabilities);
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
