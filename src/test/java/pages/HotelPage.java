package pages;

import io.qameta.allure.Step;
import magnitude.base.MagnitudePage;
import magnitude.comparison.image.ImageComparison;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import magnitude.element.ScreenEnable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import utils.data.RateInfo;

import javax.imageio.ImageIO;

import static utils.interfaces.DriverConfiguration.getDriverName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HotelPage extends MagnitudePage {
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".js-booked__header")
    private WebElement header;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".h-page__header")
    public WebElement hotelName;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".js-gallery-wrapper ")
    public WebElement gallery;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".check-form-wrapper")
    public WebElement checkAvail;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".h-page__navigation")
    public WebElement menu;
    @ScreenEnable
    @FindBy(how = How.ID, using = "overview")
    public WebElement overview;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".h-page__convenience")
    public WebElement highlights;
    @ScreenEnable
    @FindBy(how = How.ID, using = "facilities")
    public WebElement facilities;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".h-page__distance")
    public WebElement distance;
    @ScreenEnable
    @FindBy(how = How.ID, using = "rooms")
    public WebElement rooms;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".h-page__policies")
    public WebElement policies;
    @ScreenEnable
    @FindBy(how = How.ID, using = "location")
    public WebElement location;
    @ScreenEnable
    @FindBy(how = How.ID, using = "reviews")
    public WebElement reviews;
    @ScreenEnable
    @FindBy(how = How.CSS, using = ".new-hp-footer")
    public WebElement footer;
    By selectedRateRoomNameLocator = By.cssSelector("span.rooms-title-txt");
    By selectedRateCancellationPolicyLocator = By.cssSelector("span.cancel-text");
    By selectedRatePriceLocator = By.cssSelector("span.rate-price");
    By rateLocator = By.cssSelector(".rates-item");
    By bookButtonLocator = By.cssSelector(".book-room-btn");
    By rateCancellationPolicyLocator = By.cssSelector("span.cancel-text");
    By ratePriceLocator = By.cssSelector("span.rate-price");
    By roomTitleLocator = By.cssSelector("span.rooms-title-txt");
    @FindBy(how = How.XPATH, using = "(//button[contains(@class, 'js-get-price-btn')])[1]")
    private WebElement availButton;
    @FindBy(how = How.CSS, using = ".h-page__navigation-check-avail .js-get-price-btn")
    private WebElement availButtonInStickyMenu;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[1]//a)[1]")
    private WebElement overviewMainMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[2]//a)[1]")
    private WebElement overviewStickyMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[1]//a)[2]")
    private WebElement facilitiesMainMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[2]//a)[2]")
    private WebElement facilitiesStickyMenuButton;

    //Calendar
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[1]//a)[3]")
    private WebElement roomsMainMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[2]//a)[3]")
    private WebElement roomsStickyMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[1]//a)[4]")
    private WebElement locationMainMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[2]//a)[4]")
    private WebElement locationStickyMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[1]//a)[5]")
    private WebElement reviewsMainMenuButton;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__navigation')])[2]//a)[5]")
    private WebElement reviewsStickyMenuButton;
    @FindBy(how = How.CSS, using = "")
    private WebElement roomsAvailError;
    @FindBy(how = How.CSS, using = "")
    private WebElement waitRatesAlert;
    @FindBy(how = How.CSS, using = ".js-open-calendar")
    private WebElement checkInField;
    @FindBy(how = How.CSS, using = ".h-page__navigation-check-avail .js-open-calendar")
    private WebElement checkInFieldInStickyMenu;
    @FindBy(how = How.CSS, using = ".h-page__check-avail .date-picker-wrapper")
    private WebElement calendar;
    @FindBy(how = How.CSS, using = ".h-page__navigation-check-avail .date-picker-wrapper")
    private WebElement calendarInStickyMenu;
    @FindBy(how = How.CSS, using = ".h-page__check-avail .day.toMonth.valid")
    private List<WebElement> validCalendarDates;
    @FindBy(how = How.CSS, using = ".h-page__navigation-check-avail .day.toMonth.valid")
    private List<WebElement> validCalendarDatesInStickyMenu;
    @FindBy(how = How.CSS, using = ".maps-link")
    private WebElement showOnMapButtonInAddressField;
    @FindBy(how = How.CSS, using = ".js-fixed-navbar")
    private WebElement stickyMenu;
    @FindBy(how = How.CSS, using = ".js-fixed-navbar .check-form")
    private WebElement checkAvailInStickyMenu;
    @FindBy(how = How.CSS, using = ".scroll_to_container")
    private WebElement showOnMapButtonInHotelGallery;
    @FindBy(how = How.CSS, using = ".h-page__body-sidebar .rate-info-subtitle .rate-info-link")
    private WebElement hotelReviewsInHighlights;
    @FindBy(how = How.CSS, using = ".gallery-left-column .js-start-images-gallery")
    private WebElement photoInHotelGallery;
    @FindBy(how = How.CSS, using = ".swipe-area-action__btn_right")
    private WebElement nextPhotoButtonInHotelGallery;
    @FindBy(how = How.CSS, using = ".js-gallerys-wrapper .swipe-area-action__btn_right")
    private WebElement nextPhotoButtonInRoomGallery;
    @FindBy(how = How.CSS, using = ".swipe-area-action__btn_left")
    private WebElement prevPhotoButtonInHotelGallery;
    @FindBy(how = How.CSS, using = ".js-gallerys-wrapper .swipe-area-action__btn_left")
    private WebElement prevPhotoButtonInRoomGallery;
    @FindBy(how = How.CSS, using = ".h-p-hotel-gallery__close.js-close-gallery-item")
    private WebElement closeHotelGalleryButton;
    @FindBy(how = How.CSS, using = ".js-gallerys-wrapper .h-p-hotel-gallery__close.js-close-gallery-item")
    private WebElement closeRoomGalleryButton;
    @FindBy(how = How.CSS, using = ".slick-current img")
    private WebElement activePhotoInGallery;
    @FindBy(how = How.CSS, using = ".js-gallerys-wrapper .slick-current img")
    private WebElement activePhotoInRoomGallery;
    @FindBy(how = How.CSS, using = ".js-gtm-room-gallery")
    private List<WebElement> defaultRoomsPhotos;
    @FindBy(how = How.CSS, using = ".gallerys-wrapper_room .h-p-hotel-gallery__nav__item")
    private List<WebElement> sliderRoomPhotos;
    @FindBy(how = How.CSS, using = ".more-less-facilities")
    private List<WebElement> moreInfoDefaultRooms;
    @FindBy(how = How.CSS, using = ".js-r-and-g-btn")
    private WebElement guestAndRoomsSelectorButton;
    @FindBy(how = How.CSS, using = ".js-r-and-g-container")
    private WebElement guestAndRoomsPopUp;
    @FindBy(how = How.CSS, using = ".js-add-room-btn")
    private WebElement addRoomButton;
    @FindBy(how = How.CSS, using = ".r-and-g-container div[data-room-id]")
    private List<WebElement> roomsList;
    //SEO
    //
    @FindBy(how = How.CSS, using = ".rates-multibooking-title-wrapper")
    private List<WebElement> multibookingRoomSelectionSectionTabs;
    @FindBy(how = How.CSS, using = ".multi-booking-in")
    private List<WebElement> multibookingRoomSelectionSections;
    @FindBy(how = How.CSS, using = ".selected-remove-room")
    private List<WebElement> cancelMultibookingSelectionButtons;
    @FindBy(how = How.CSS, using = ".multibooking-selected-wrapper")
    private WebElement multibookingBillPopUp;
    @FindBy(how = How.CSS, using = ".multibooking-selected-wrapper .selected-rates-wrapper")
    private List<WebElement> multibookingBillRate;
    @FindBy(how = How.CSS, using = ".hotels-neighbors-wrap")
    private WebElement hotelsLinksBlock;
    @FindBy(how = How.CSS, using = ".nearby-hotels-wrap")
    private WebElement nearbyHotelsBlock;
    @FindBy(how = How.CSS, using = ".nearby-hotels-container .hotels-neighbors-wrap")
    private WebElement nearbyCitiesBlock;
    @FindBy(how = How.CSS, using = ".booked__seo-footer")
    private WebElement localVersionsBlock;
    //More - less information buttons
    @FindBy(how = How.CSS, using = "#overview .more-btn")
    private WebElement moreInformationButtonInOverview;
    @FindBy(how = How.CSS, using = "#overview .less-btn")
    private WebElement lessInformationButtonInOverview;
    @FindBy(how = How.CSS, using = "#facilities .more-btn")
    private WebElement moreInformationButtonInFacilities;
    @FindBy(how = How.CSS, using = "#facilities .less-btn")
    private WebElement lessInformationButtonInFacilities;
    @FindBy(how = How.CSS, using = ".h-page__body-sidebar .h-page__distance .more-btn")
    private WebElement moreInformationButtonInTransport;
    @FindBy(how = How.CSS, using = ".h-page__body-sidebar .h-page__distance .less-btn")
    private WebElement lessInformationButtonInTransport;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__policies')])[2]//span[contains(@class, 'more-btn')])[2]")
    private WebElement moreInformationButtonInImportantInformation;
    @FindBy(how = How.XPATH, using = "((//div[contains(@class, 'h-page__policies')])[2]//span[contains(@class, 'less-btn')])[2]")
    private WebElement lessInformationButtonInImportantInformation;
    @FindBy(how = How.CSS, using = "#rooms .g-more-less-wrap .more-btn")
    private WebElement moreInformationButtonInRooms;
    @FindBy(how = How.CSS, using = "#rooms .g-more-less-wrap .less-btn")
    private WebElement lessInformationButtonInRooms;
    @FindBy(how = How.CSS, using = "#reviews .more-btn")
    private WebElement moreInformationButtonInReviews;
    @FindBy(how = How.CSS, using = "#reviews .less-btn")
    private WebElement lessInformationButtonInReviews;
    @FindBy(how = How.CSS, using = ".write-review-btn")
    private WebElement writeReviewButton;

    //Magnitude
    @FindBy(how = How.CSS, using = ".js-fixed-nav-open-check-avail ")
    private WebElement openAvailFormInStickyMenuButton;
    @FindBy(how = How.CSS, using = ".js-fixed-navbar .fixed-form-close-btn")
    private WebElement closeAvailFormInStickyMenuButton;
    @FindBy(how = How.CSS, using = ".js-btn-check-prices")
    private List<WebElement> checkPrices;
    @FindBy(how = How.CSS, using = ".js-gallerys-wrapper")
    private WebElement hotelGallery;
    @FindBy(how = How.CSS, using = ".h-page__room-blank .js-gallerys-wrapper")
    private List<WebElement> roomGalleries;
    @FindBy(how = How.CSS, using = ".multibooking-selected-wrapper .book-now-btn_wrapper")
    private WebElement bookMultibookingSelectionButton;

    public HotelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step
    public ClickUnderPage checkAvail() {
        availButton.click();
        return new ClickUnderPage(driver);
    }

    @Step
    public HotelPage openHotelRooms() {
        rooms.click();
        waiter.pauseFor1Second();
        return this;
    }

    @Step
    public HotelPage enterDefaultCheckInCheckOutDate() {
        checkInField.click();
        waiter.waitFor(visibilityOf(calendar), 10);
        selectTomorrowDate();
        selectDayAfterTomorrow();
        return this;
    }

    @Step
    public HotelPage enterCheckInCheckOutDate(int startDay) {
        checkInField.click();
        waiter.waitFor(visibilityOf(calendar), 10);
        selectCheckInDateDate(startDay);
        selectTomorrowDate();
        return this;
    }

    @Step
    public HotelPage enterDefaultCheckInCheckOutDateInStickyMenu() {
        checkInFieldInStickyMenu.click();
        waiter.waitFor(visibilityOf(calendarInStickyMenu), 10);
        selectTomorrowDateInStickyMenu();
        selectDayAfterTomorrowInStickyMenu();
        return this;
    }

    @Step
    private void selectDayAfterTomorrowInStickyMenu() {
        waiter.waitFor(elementToBeClickable(validCalendarDatesInStickyMenu.get(2)));
        validCalendarDatesInStickyMenu.get(1).click();
    }

    @Step
    private void selectTomorrowDateInStickyMenu() {
        waiter.waitFor(elementToBeClickable(validCalendarDatesInStickyMenu.get(1)));
        validCalendarDatesInStickyMenu.get(1).click();
    }

    @Step
    private void selectDayAfterTomorrow() {
        waiter.waitFor(elementToBeClickable(validCalendarDates.get(2)));
        //new Actions(driver).moveToElement(validCalendarDates.get(1));
        validCalendarDates.get(1).click();
    }

    @Step
    private void selectTomorrowDate() {
        waiter.waitFor(elementToBeClickable(validCalendarDates.get(1)));
        validCalendarDates.get(1).click();
    }

    @Step
    private void selectCheckInDateDate(int day) {
        waiter.waitFor(elementToBeClickable(validCalendarDates.get(day)));
        validCalendarDates.get(day).click();
    }

    @Step
    public HotelPage clickOnShowOnMapButtonInAddressFiled() {
        showOnMapButtonInAddressField.click();
        checkLocationScroll();
        return this;
    }

    @Step
    public HotelPage clickOnShowOnMapButtonInHotelGallery() {
        showOnMapButtonInHotelGallery.click();
        checkLocationScroll();
        return this;
    }

    @Step
    private void checkLocationScroll() {
        waiter.pauseFor1Second();
        Long value = getCurrentPositionOnPage();
        assertThat("Scroll didn`t work properly. Final destination point is different", value.toString(),
                is(String.valueOf(location.getLocation().getY()
                        - stickyMenu.getSize().getHeight() / 2 - 1)));
    }

    @Step
    public HotelPage clickOnOverviewStickyMenuButton() {
        overviewStickyMenuButton.click();
        checkOverviewScroll();
        return this;
    }

    @Step
    private void checkOverviewScroll() {
        waiter.pauseFor1Second();
        Long value = getCurrentPositionOnPage();
        assertThat("Scroll didn`t work properly. Final destination point is different", value.toString(),
                is(String.valueOf(overview.getLocation().getY()
                        - stickyMenu.getSize().getHeight() / 2 - 1)));
    }

    @Step
    public HotelPage clickOnLocationButtonInStickyMenu() {
        locationStickyMenuButton.click();
        checkLocationScroll();
        return this;
    }

    @Step
    public HotelPage clickOnHotelReviewsInHighlights() {
        waiter.waitFor(elementToBeClickable(hotelReviewsInHighlights));
        hotelReviewsInHighlights.click();
        checkReviewsScroll();
        return this;
    }

    @Step
    private void checkReviewsScroll() {
        waiter.pauseFor1Second();
        Long value = getCurrentPositionOnPage();
        assertThat("Scroll didn`t work properly. Final destination point is different", value.toString(),
                is(String.valueOf(reviews.getLocation().getY()
                        - stickyMenu.getSize().getHeight() / 2 - 1)));
    }

    @Step
    public HotelPage clickOnReviewsInStickyMenu() {
        reviewsStickyMenuButton.click();
        checkReviewsScroll();
        return this;
    }

    @Step
    public HotelPage clickOnPhotoInHotelGallery() {
        waiter.waitFor(elementToBeClickable(photoInHotelGallery));
        photoInHotelGallery.click();
        if (getDriverName(driver).equals(BrowserType.SAFARI))
            photoInHotelGallery.click();
        waiter.waitFor(visibilityOf(activePhotoInGallery), 10);
        return this;
    }

    public String getCurrentPhotoSrc() {
        waiter.waitFor(ExpectedConditions.attributeToBeNotEmpty(activePhotoInGallery, "src"));
        return activePhotoInGallery.getAttribute("src");
    }

    public String getCurrentRoomPhotoSrc() {
        waiter.pauseFor1Second();
        waiter.waitFor(ExpectedConditions.attributeToBeNotEmpty(activePhotoInRoomGallery, "src"));
        return activePhotoInRoomGallery.getAttribute("src");
    }

    @Step
    public HotelPage clickOnNextPhotoButtonInHotelGallery() {
        nextPhotoButtonInHotelGallery.click();
        waiter.pauseFor1Second();
        waiter.waitFor(visibilityOf(activePhotoInGallery));
        return this;
    }

    @Step
    public HotelPage clickOnPrevPhotoButtonInHotelGallery() {
        prevPhotoButtonInHotelGallery.click();
        waiter.pauseFor1Second();
        waiter.waitFor(visibilityOf(activePhotoInGallery));
        return this;
    }

    @Step
    public HotelPage clickOnCloseHotelGalleryButton() {
        closeHotelGalleryButton.click();
        return this;
    }

    @Step
    public boolean checkThatGalleryDisplayed() {
        return hotelGallery.isDisplayed();
    }

    public boolean checkThatRoomGalleryDisplayed(int roomNumber) {
        return roomGalleries.get(roomNumber - 1).isDisplayed();
    }

    @Step
    public HotelPage clickOnFreeSpaceInHotelGallery() {
        Actions action = new Actions(driver);
        action.moveToElement(nextPhotoButtonInHotelGallery)
                .moveByOffset(100, 0).click().build().perform();
        return this;
    }

    @Step
    public HotelPage checkSeoBlock() {
        try {
            assertThat("Suggestion hotels block isn`t displayed", hotelsLinksBlock.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Suggestion hotels block isn`t present");
        }
        try {
            assertThat("Nearby hotels block isn`t displayed", nearbyHotelsBlock.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Nearby hotels block isn`t present");
        }
        try {
            assertThat("Nearby cities block isn`t displayed", nearbyCitiesBlock.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Nearby cities block isn`t present");
        }
        try {
            assertThat("Local site versions block isn`t displayed", localVersionsBlock.isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.fail("Local site versions block isn`t present");
        }
        checkVisibleLinksCount(hotelsLinksBlock);
        checkVisibleLinksCount(nearbyHotelsBlock);
        checkVisibleLinksCount(nearbyCitiesBlock);
        checkVisibleLinksCount(localVersionsBlock);
        return this;
    }

    @Step
    private void checkVisibleLinksCount(WebElement block) {
        List<WebElement> links = block.findElements(By.cssSelector("a"));
        AtomicInteger count = new AtomicInteger();
        links.forEach(link -> {
            if (link.isDisplayed())
                count.getAndIncrement();
        });
        assertThat("0 visible links", count.get() > 0);
    }

    @Step
    public HotelPage clickOnMoreInformationButtonInOverview() {
        waiter.waitFor(elementToBeClickable(moreInformationButtonInOverview));
        moreInformationButtonInOverview.click();
        return this;
    }

    @Step
    public HotelPage clickOnLessInformationButtonInOverview() {
        waiter.waitFor(elementToBeClickable(lessInformationButtonInOverview));
        lessInformationButtonInOverview.click();
        return this;
    }

    @Step
    public HotelPage clickOnMoreInformationButtonInFacilities() {
        waiter.waitFor(elementToBeClickable(moreInformationButtonInFacilities));
        moreInformationButtonInFacilities.click();
        return this;
    }

    @Step
    public HotelPage clickOnLessInformationButtonInFacilities() {
        waiter.waitFor(elementToBeClickable(lessInformationButtonInFacilities));
        lessInformationButtonInFacilities.click();
        return this;
    }

    @Step
    public HotelPage clickOnMoreInformationButtonInTransport() {
        waiter.waitFor(elementToBeClickable(moreInformationButtonInTransport));
        moreInformationButtonInTransport.click();
        return this;
    }

    @Step
    public HotelPage clickOnLessInformationButtonInTransport() {
        waiter.waitFor(elementToBeClickable(lessInformationButtonInTransport));
        lessInformationButtonInTransport.click();
        return this;
    }

    @Step
    public HotelPage clickOnMoreInformationButtonInImportantInformation() {
        waiter.waitFor(elementToBeClickable(moreInformationButtonInImportantInformation));
        moreInformationButtonInImportantInformation.click();
        return this;
    }

    @Step
    public HotelPage clickOnLessInformationButtonInImportantInformation() {
        waiter.waitFor(elementToBeClickable(lessInformationButtonInImportantInformation));
        lessInformationButtonInImportantInformation.click();
        return this;
    }

    @Step
    public HotelPage clickOnMoreInformationButtonInRooms() {
        waiter.waitFor(elementToBeClickable(moreInformationButtonInRooms));
        moreInformationButtonInRooms.click();
        return this;
    }

    @Step
    public HotelPage clickOnLessInformationButtonInRooms() {
        waiter.waitFor(elementToBeClickable(lessInformationButtonInRooms));
        lessInformationButtonInRooms.click();
        return this;
    }

    @Step
    public HotelPage clickOnMoreInformationButtonInReviews() {
        waiter.waitFor(elementToBeClickable(moreInformationButtonInReviews));
        moreInformationButtonInReviews.click();
        return this;
    }

    @Step
    public HotelPage clickOnLessInformationButtonInReviews() {
        waiter.waitFor(elementToBeClickable(lessInformationButtonInReviews));
        click(lessInformationButtonInReviews);
        return this;
    }

    @Step
    public HotelPage clickOnWriteReviewButton() {
        waiter.waitFor(elementToBeClickable(writeReviewButton));
        click(writeReviewButton);
        String currentPage = driver.getWindowHandle();
        waiter.waitFor(numberOfWindowsToBe(2));
        driver.getWindowHandles().forEach(name -> {
            if (!name.equals(currentPage))
                driver.switchTo().window(name);
        });
        waiter.waitForPageLoaded();
        assertThat("", driver.getCurrentUrl(), endsWith("writereview.html"));
        return this;
    }

    @Step
    public HotelPage openAvailFormInStickyMenu() {
        waiter.waitFor(elementToBeClickable(openAvailFormInStickyMenuButton));
        click(openAvailFormInStickyMenuButton);
        return this;
    }

    @Step
    public ClickUnderPage checkAvailInStickyMenu() {
        click(availButtonInStickyMenu);
        return new ClickUnderPage(driver);
    }

    @Step
    public HotelPage waitForStickyMenuDisplayed() {
        waiter.waitFor(visibilityOf(checkAvailInStickyMenu), 10);
        return this;
    }

    @Step
    public HotelPage waitForStickyMenuHided() {
        waiter.waitFor(invisibilityOf(checkAvailInStickyMenu), 10);
        return this;
    }

    @Step
    public HotelPage closeAvailFormInStickyMenu() {
        click(closeAvailFormInStickyMenuButton);
        return this;
    }

    @Step
    public HotelPage clickOnFirstCheckPricesButtonInRooms() {
        click(checkPrices.get(0));
        return this;
    }

    public void prepareForScreening() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public HotelPage openRoomGalleryByClickOnRoomPhoto(int room) {
        click(defaultRoomsPhotos.get(room - 1));
        waiter.pauseFor1Second();
        waiter.waitFor(visibilityOf(roomGalleries.get(room - 1)));
        return this;
    }

    @Step
    public HotelPage clickOnNextPhotoButtonInRoomGallery() {
        click(nextPhotoButtonInRoomGallery);
        waiter.pauseFor1Second();
        waiter.waitFor(visibilityOf(activePhotoInRoomGallery));
        return this;
    }

    @Step
    public HotelPage clickOnPrevPhotoButtonInRoomGallery() {
        click(prevPhotoButtonInRoomGallery);
        waiter.pauseFor1Second();
        waiter.waitFor(visibilityOf(activePhotoInRoomGallery));
        return this;
    }

    @Step
    public HotelPage clickOnPhotoInRoomGallerySlider(int sliderPhoto) {
        sliderRoomPhotos.get(sliderPhoto - 1).click();
        waiter.pauseFor1Second();
        waiter.waitFor(visibilityOf(activePhotoInRoomGallery));
        return this;
    }

    public String getSliderPhotoSrc(int sliderPhoto) {
        return sliderRoomPhotos.get(sliderPhoto - 1).findElement(By.cssSelector("img")).getAttribute("src");
    }

    public String getLastSliderPhotoSrc() {
        waiter.waitFor(ExpectedConditions.attributeToBeNotEmpty(sliderRoomPhotos.get(sliderRoomPhotos.size() - 1).findElement(By.cssSelector("img")), "src"));
        return sliderRoomPhotos.get(sliderRoomPhotos.size() - 1).findElement(By.cssSelector("img")).getAttribute("src");
    }

    @Step
    public HotelPage clickOnCloseRoomGalleryButton() {
        closeRoomGalleryButton.click();
        waiter.pauseFor1Second();
        waiter.waitFor(invisibilityOf(activePhotoInRoomGallery));
        return this;
    }

    @Step
    public HotelPage openRoomGalleryByClickOnMoreInformationButton(int roomNumber) {
        moreInfoDefaultRooms.get(roomNumber - 1).click();
        waiter.waitFor(visibilityOf(roomGalleries.get(roomNumber - 1)));
        return this;
    }

    @Step
    public HotelPage waitForFinishingMultibookingAvail() {
        waiter.waitFor(presenceOfElementLocated(By.cssSelector("#hotel_tab_rooms")), 120);
        waiter.waitFor(visibilityOfAllElements(multibookingRoomSelectionSectionTabs), 120);
        return this;
    }

    @Step
    public HotelPage waitForFinishingAvail() {
        waiter.waitFor(presenceOfElementLocated(By.cssSelector("#hotel_tab_rooms")), 60);
        waiter.waitFor(visibilityOfElementLocated(By.cssSelector("#hotel_tab_rooms")), 60);
        waiter.waitFor(visibilityOfElementLocated(By.cssSelector("div[data-ng-if=\"rooms.status == 'ready'\"]")), 60);
        return this;
    }

    @Step
    public HotelPage selectRoomsNumber(int roomsCount) {
        guestAndRoomsSelectorButton.click();
        waiter.waitFor(visibilityOf(guestAndRoomsPopUp));
        while (roomsList.size() < roomsCount + 1) {
            addRoomButton.click();
        }
        return null;
    }

    @Step
    public HotelPage toggleRoomSelectionInMultibookingMode(int roomNumber) {
        multibookingRoomSelectionSectionTabs.get(roomNumber - 1).click();
        return this;
    }

    public WebElement getMultibookingSelectionSection(int roomNumber) {
        return multibookingRoomSelectionSections.get(roomNumber - 1);
    }

    public WebElement getMultibookingBillPopUp() {
        return multibookingBillPopUp;
    }

    @Step
    public HotelPage selectRateInMultibookingMode(int roomNumber, int rateNumber) {
        multibookingRoomSelectionSections.get(roomNumber - 1)
                .findElements(rateLocator).get(rateNumber - 1)
                .findElement(bookButtonLocator).click();
        return this;
    }

    @Step
    public RateInfo getRateInfoInMultibookingMode(int roomNumber, int rateNumber) {
        RateInfo rateInfo = new RateInfo();
        WebElement room = multibookingRoomSelectionSections.get(roomNumber - 1);
        rateInfo.setRoomName(room.findElement(roomTitleLocator).getText());
        WebElement rate = room.findElements(rateLocator).get(rateNumber - 1);
        rateInfo.setPrice(rate.findElement(ratePriceLocator).getText());
        rateInfo.setCancellationPolicy(rate.findElement(rateCancellationPolicyLocator).getText());
        return rateInfo;
    }

    @Step
    public RateInfo getRateInfoInMultibookingBill(int rateNumber) {
        RateInfo rateInfo = new RateInfo();
        WebElement rate = multibookingBillRate.get(rateNumber - 1);
        rateInfo.setRoomName(rate.findElement(selectedRateRoomNameLocator).getText());
        rateInfo.setPrice(rate.findElement(selectedRatePriceLocator).getText());
        rateInfo.setCancellationPolicy(rate.findElement(selectedRateCancellationPolicyLocator).getText());
        return rateInfo;
    }

    @Step
    public HotelPage discardRateInMultibookingMode(int rateNumber) {
        cancelMultibookingSelectionButtons.get(rateNumber - 1).click();
        return this;
    }

    @Step
    public MagnitudePage confirmMultibookingSelections() {
        bookMultibookingSelectionButton.click();
//        return new ContactDetailsPage(driver);
        waiter.waitFor(urlContains("page=conf_wait"));
        return this;
    }
    public static void main( String[] args ) throws IOException {
        // load the images to be compared
        BufferedImage image1 = ImageIO.read( new File( "header.png" )  );
        BufferedImage image2 = ImageIO.read( new File( "header2.png" )  );

        // where to save the result (leave null if you want to see the result in the UI)
        File result = new File( "result.png" );

        // compare them
        BufferedImage drawnDifferences = new ImageComparison( image1, image2, result ).compareImages();
    }
}
