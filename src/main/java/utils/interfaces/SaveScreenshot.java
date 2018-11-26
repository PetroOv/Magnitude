package utils.interfaces;

import io.qameta.allure.Attachment;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static utils.interfaces.DriverConfiguration.getDriverName;

public interface SaveScreenshot {

    @Attachment("{imageName}")
    default byte[] attachImage(String imageName, BufferedImage img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    default BufferedImage getFullPageScreenShot(WebDriver driver) {
        if (driver == null)
            Assert.fail("driver is down");
        AShot aShot;
        if (getDriverName(driver).equals(BrowserType.SAFARI))
            return new AShot().takeScreenshot(driver).getImage();
        else {
            aShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000));
        }
        BufferedImage bufferedImage = null;
        bufferedImage = aShot.takeScreenshot(driver).getImage();
        return bufferedImage;
    }

    default BufferedImage getPageScreenShot(WebDriver driver) {
        if (driver == null)
            Assert.fail("driver is down");
        return new AShot().takeScreenshot(driver).getImage();
    }
}
