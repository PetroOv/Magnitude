package magnitude.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionWorker {
    private final WebDriver driver;

    public ActionWorker(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element){
        element.click();
    }
    public void click(By locator){
        driver.findElements(locator).get(0).click();
    }
    public String getText(WebElement element){
        return element.getText();
    }
    public String getText(By locator){
        return driver.findElements(locator).get(0).getText();
    }
    public void sendKeys(WebElement element, CharSequence... charSequences){
        element.sendKeys(charSequences);
    }
    public void sendKeys(By locator, CharSequence... charSequences){
        driver.findElements(locator).get(0).sendKeys(charSequences);
    }
}
