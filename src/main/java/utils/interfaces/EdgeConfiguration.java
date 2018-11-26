package utils.interfaces;

import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

public interface EdgeConfiguration {
    default void forEdge(WebDriver driver) {
        driver.manage().window().maximize();
        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_P);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_P);
            Thread.sleep(3000);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
        driver.getWindowHandle();
        String subWindowHandler;
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            subWindowHandler = handle;
            driver.switchTo().window(subWindowHandler);
        }
    }
}
