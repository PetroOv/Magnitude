package magnitude.element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.awt.image.BufferedImage;

public class ElementScreenShot {
    public static BufferedImage getElementScreenShot(Element element, WebDriver webDriver){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("document.querySelector('body').setAttribute('style', 'overflow:hidden')");
        return new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(webDriver, element.getWebElement(webDriver))
                .getImage();
    }
    public static BufferedImage getElementScreenShot(WebElement element, WebDriver webDriver){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("document.querySelector('body').setAttribute('style', 'overflow:hidden')");
        return new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(webDriver, element)
                .getImage();
    }

}
