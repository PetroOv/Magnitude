package utils.interfaces;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.OutputType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public interface BrowserFullPageScreenshot {
    default <X> X getFullScreenshotAs(OutputType<X> outputType) {
        Object metrics = sendEvaluate(
                "({" +
                        "width: Math.max(window.innerWidth,document.body.scrollWidth,document.documentElement.scrollWidth)|0," +
                        "height: Math.max(window.innerHeight,document.body.scrollHeight,document.documentElement.scrollHeight)|0," +
                        "deviceScaleFactor: window.devicePixelRatio || 1," +
                        "mobile: typeof window.orientation !== 'undefined'" +
                        "})");
        sendCommand("Emulation.setDeviceMetricsOverride", metrics);
        Object result = sendCommand("Page.captureScreenshot", ImmutableMap.of("format", "png", "fromSurface", true));
        sendCommand("Emulation.clearDeviceMetricsOverride", ImmutableMap.of());
        String base64EncodedPng = (String) ((Map<String, ?>) result).get("data");
        return outputType.convertFromBase64Png(base64EncodedPng);
    }

    default BufferedImage getFullScreenshotAs() {
        Object metrics = sendEvaluate(
                "({" +
                        "width: Math.max(window.innerWidth,document.body.scrollWidth,document.documentElement.scrollWidth)|0," +
                        "height: Math.max(window.innerHeight,document.body.scrollHeight,document.documentElement.scrollHeight)|0," +
                        "deviceScaleFactor: window.devicePixelRatio || 1," +
                        "mobile: typeof window.orientation !== 'undefined'" +
                        "})");
        sendCommand("Emulation.setDeviceMetricsOverride", metrics);
        Object result = sendCommand("Page.captureScreenshot", ImmutableMap.of("format", "png", "fromSurface", true));
        sendCommand("Emulation.clearDeviceMetricsOverride", ImmutableMap.of());
        String base64EncodedPng = (String) ((Map<String, ?>) result).get("data");
        try {
            return ImageIO.read(OutputType.FILE.convertFromBase64Png(base64EncodedPng));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedImage(1, 1, 1);
    }

    Object sendCommand(String cmd, Object params);

    Object sendEvaluate(String script);
}
