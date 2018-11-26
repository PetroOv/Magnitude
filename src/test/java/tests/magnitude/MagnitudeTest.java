package tests.magnitude;

import io.github.bonigarcia.wdm.WebDriverManager;
import magnitude.base.MagnitudeBaseTest;
import magnitude.element.Element;
import magnitude.element.ScreenSaver;
import magnitude.pages.Pages;
import magnitude.pages.Resolution;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.HotelPage;
import utils.configurations.DriverManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static magnitude.element.Element.initElements;
import static magnitude.element.ElementScreenShot.getElementScreenShot;
import static magnitude.element.ImageMatcher.attachImage;
import static magnitude.element.ImageMatcher.compareImages;
import static magnitude.element.ScreenSaver.ImageFormat.PNG;

@RunWith(Parameterized.class)
public class MagnitudeTest extends MagnitudeBaseTest {
    private static final String lang = "&langID=";
    private static Pages pages;
    private static String currentFolder;
    private static ScreenSaver screenSaver;
    @Parameterized.Parameter
    public String page;
    @Parameterized.Parameter(1)
    public int language;
    @Parameterized.Parameter(2)
    public Resolution resolution;
    @Parameterized.Parameter(3)
    public String resolutionName;
    @Parameterized.Parameter(4)
    public String blockName;
    @Parameterized.Parameter(5)
    public String pageName;
    WebDriver driver;
    private String testName;
    private String previousFileName;

    //    @Rule
//    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
//            .withDesiredCapabilities(DesiredCapabilities.chrome())
//            .withRecordingMode(RECORD_ALL, new File("target"));
//Todo driver manager, base test , base page, test example.
    @BeforeClass
    public static void init() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        currentFolder = "./screenshots/" + format1.format(cal.getTime());
        screenSaver = new ScreenSaver(currentFolder);
        WebDriverManager.chromedriver().setup();
    }

    @Parameterized.Parameters(name = "{0}, lang = {1}, {3}, {4}")
    public static Collection<Object[]> data() {
        List<Object[]> environment = new ArrayList<>();
        if (pages == null)
            pages = given().get("https://jsonblob.com/api/jsonBlob/e71aa974-90b3-11e8-ad06-c733736063c5")
                    .andReturn().getBody().as(Pages.class);
//        System.out.println(json);
//        pages = new Gson().fromJson(json, Pages.class);
        pages.getHotelPages().forEach(page -> {
            pages.getLanguages().forEach(language -> {
                pages.getResolutions().forEach(resolution -> {
                    pages.getHotelBlocks().forEach(block -> {
                        environment.add(new Object[]{page.getUrl(), language, resolution, resolution.getName(), block, page.getName()});
                    });
                });
            });
        });
        return environment;
    }

    @Before
    public void setUp() throws Exception {
        driver = driverManager.initDriver();
        driverManager.setDriverSize(driver, resolution.getWidth(), resolution.getHeight());
        driver.get(page.replace("release", "net") + lang + language);
        testName = String.format("%s %d %s %s", pageName, language, resolution.getName(), blockName);
    }
    //todo добавить взятие елемента по имени с ПО модели

    @After
    public void tearDown() {
        driverManager.quitDriver(driver);
    }

    @Test
    public void name() {
        HotelPage hp = new HotelPage(driver);
        hp.prepareForScreening();
        List<String> blocks = new ArrayList<>();
        blocks.add(blockName);
        List<Element> screens = initElements(hp, blocks);
        screens.forEach((screen) -> {
            BufferedImage image = getElementScreenShot(screen, driver);
            File imageFile = screenSaver.saveImage(image, screen.getName(), PNG);
            attachImage(image, screen.getName());
            if (pages.isCompare()) {
                compareImages(screen, Paths.get("./screenshots"), PNG, imageFile);
            }
        });
    }
}
