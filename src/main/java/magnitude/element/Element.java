package magnitude.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static magnitude.element.ElementScreenShot.getElementScreenShot;

public class Element {
    private By locator;
    private WebElement element;
    private Point location;
    private String name;
    private BufferedImage elementImage;

    public Element(String name, WebElement element) {
        this.element = element;
        this.name = name;
    }

    public void initElement(WebDriver webDriver){
        element = webDriver.findElement(locator);
        location = element.getLocation();
    }

    public String getName() {
        return name;
    }

    public BufferedImage getElementImage() {
        return elementImage;
    }

    public void setElementImage(WebDriver driver) {
        this.elementImage = getElementScreenShot(this, driver);
    }

    public void setElementImage(BufferedImage elementImage) {
        this.elementImage = elementImage;
    }

    public WebElement getWebElement(WebDriver webDriver){
        if(element == null)
            element = webDriver.findElement(locator);
        return element;
    }

    public Point getLocation() {
        return location;
    }

    public static WebElement init(Object claz){
        Map<String, WebElement> map = new HashMap<>();
        Field[] fields = claz.getClass().getFields();
        for (Field field : fields) {
            if (field.getType().equals(WebElement.class)) {
                System.out.println(field.getName());
                field.setAccessible(true);
                String name = field.getName();
                try {
                    Object value = field.get(claz);
                    return (WebElement) value;

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static List<Element> initElements(Object claz){
        List<Element> result = new ArrayList<>();
        Field[] fields = claz.getClass().getFields();
        for (Field field : fields) {
            if (field.getType().equals(WebElement.class)
                    && field.isAnnotationPresent(ScreenEnable.class)) {
                System.out.println(field.getName());
                field.setAccessible(true);
                String name = field.getName();
                getElementFromPage(claz, result, field, name);
            }
        }
        return result;
    }
    public static List<Element> initElements(Object claz, List<String> requiredElements){
        List<Element> result = new ArrayList<>();
        Field[] fields = claz.getClass().getFields();
        for (Field field : fields) {
            if (field.getType().equals(WebElement.class)
                    && requiredElements.contains(field.getName())
                    && field.isAnnotationPresent(ScreenEnable.class)) {
                field.setAccessible(true);
                String name = field.getName();
                getElementFromPage(claz, result, field, name);
            }
        }
        return result;
    }

    private static void getElementFromPage(Object claz, List<Element> result, Field field, String name) {
        try {
            Object value = field.get(claz);
            Element element = new Element(name, (WebElement) value);
            result.add(element);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
