package utils.data;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    public static final String POLAND_NETRATE_CONF_PAGE_CHINESE_LANGUAGE_LINK_LOCAL = "https://secure.booked.network-asp.biz/?page=conf_wait&from=2025-06-23&to=2025-06-24&roomsconf=2&total_price_cur=EUR&ShowCurrency=EUR&langID=17&hotelID=483639&aid=210&AvailRoomID=252810&roomCount252810=1";
    public static final String SPAIN_NETRATE_CONF_PAGE_LINK_LOCAL = "https://secure.booked.network-asp.biz/?page=conf_wait&from=2025-06-23&to=2025-06-24&roomsconf=2&total_price_cur=EUR&ShowCurrency=EUR&langID=20&hotelID=51373&aid=210&AvailRoomID=246160&roomCount246160=1";
    public static final String TAIWAN_NETRATE_CONF_PAGE_LINK_LOCAL = "https://secure.booked.network-asp.biz/?page=conf_wait&from=2025-06-23&to=2025-06-24&roomsconf=2&total_price_cur=EUR&ShowCurrency=EUR&langID=20&hotelID=397703&aid=210&AvailRoomID=253228&roomCount253228=1";
    public static final String UKRAINE_GDS_CONF_PAGE_LINK_LOCAL = "https://secure.booked.network-asp.biz/?page=conf_wait&from=2025-06-23&to=2025-06-24&roomsconf=2&total_price_cur=EUR&ShowCurrency=EUR&langID=20&hotelID=397703&aid=210&AvailRoomID=253228&roomCount253228=1";
    public static final String UKRAINE_GDS_CONF_PAGE_LINK2_LOCAL = "https://secure.booked.network-asp.biz/?page=conf_wait&from=2025-06-23&to=2025-06-24&roomsconf=2&total_price_cur=EUR&ShowCurrency=EUR&langID=20&hotelID=409285&aid=210&refdomain=www.booked.net&AvailRoomID=289373&roomCount289373=1";
    public static final String POLAND_NETRATE_CONF_PAGE_LINK_LOCAL = "https://secure.booked.network-asp.biz/?page=conf_wait&from=2019-01-17&to=2019-01-18&roomsconf=2&total_price_cur=EUR&ShowCurrency=EUR&langID=20&hotelID=51373&_wsl=UBiYMh033oKicLsQ&_s=Jl036cZZIqdWdnpD_3883&aid=210&refdomain=www.booked.net&AvailRoomID=32861001430&roomCount32861001430=1";
    public static final String POLAND_GDS_CONF_PAGE_LINK_LOCAL = "https://secure.booked.network-asp.biz/?page=conf_wait&from=2019-01-17&to=2019-01-18&roomsconf=2&total_price_cur=EUR&ShowCurrency=EUR&langID=20&hotelID=51373&_wsl=UBiYMh033oKicLsQ&_s=Jl036cZZIqdWdnpD_3883&aid=210&refdomain=www.booked.net&AvailRoomID=32861001423&roomCount32861001423=1";
    public static final String SPAIN_NETRATE_CONF_PAGE_LINK_PROD = "https://secure.booked.net/?page=conf_wait&from=2018-11-12&to=2018-11-13&roomsconf=2&total_price_cur=UAH&ShowCurrency=UAH&langID=1&hotelID=51373&_wsl=0CPgf7Qblx974DEm&_s=YZBXZtjQEycEGG8V_663655584&aid=943&subacc=b-n31-2018-06-05_12:30:52-i369698-s116709&refdomain=www.booked.net&AvailRoomID=32809768167&roomCount32809768167=1";
    public static final String SPAIN_GDS_CONF_PAGE_LINK_PROD = "https://secure.booked.net/?page=conf_wait&from=2018-12-20&to=2018-12-21&roomsconf=2&total_price_cur=UAH&ShowCurrency=UAH&langID=20&hotelID=51373&_wsl=KCvSjzefjWv62Fax&_s=kWg44At5NXWWzQsO_698018801&aid=943&subacc=b-n31-2018-06-05_12:30:52-i369698-s116709&refdomain=www.booked.net&AvailRoomID=33314473836&roomCount33314473836=1";
    public static final String AMARAT_HOTEL = "http://amarant-hotel-kiev.nochi.com.ua/?btest=119";
    public static final String SENATORS_PARK_HOTEL = "http://senators-park-hotel-kiev.nochi.com.ua/?btest=119";

    public static String getFirstName() {
        return new Faker().name().firstName();
    }

    public static String getFirstName(Locale locale) {
        return new Faker(locale).name().firstName();
    }

    public static String getLastName() {
        return new Faker().name().lastName();
    }

    public static String getLastName(Locale locale) {
        return new Faker(locale).name().lastName();
    }

    public static String getEmail() {
        return new Faker().internet().emailAddress();
    }

    public static String getEmail(Locale locale) {
        return new Faker(locale).internet().emailAddress();
    }

    public static String getCardNumber() {
        return RandomCreditCardNumberGenerator.generateMasterCardNumber();
//        return new Faker().finance().creditCard();
//        return  new BookedFinance(new Faker()).creditCard("SOLO");
    }

    public static String getCardNumber(String type) {

//        return new Faker().finance().creditCard();
        return new BookedFinance(new Faker()).creditCard(type);
    }

    public static String getCardExpiry() {
        return getCardExpiryMonth() + "/" + getCardExpiryYear();
    }

    private static String getCardExpiryMonth() {
        int result = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        if (result < 10)
            return "0" + result;
        return String.valueOf(result);
    }

    private static String getCardExpiryYear() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR) % 100 + ThreadLocalRandom.current().nextInt(1, 4 + 1));
    }

    public static String getCardCVV() {
        return String.valueOf(new Faker().number().numberBetween(100, 999));
    }

    public static String getCardHolder() {
        return new Faker().name().fullName();
    }

    public static String getPhoneNumber() {
        return getPhoneNumber(new Locale("uk"));
    }

    public static String getPhoneNumber(Locale locale) {
        return new Faker(locale).phoneNumber().cellPhone();
    }

    public static String getAddress() {
        return new Faker().address().streetAddress();
    }

    public static String getCity() {
        return new Faker().address().cityName();
    }

    public static String getZipCode() {
        return new Faker().address().zipCode();
    }

    public static String getAddress(Locale locale) {
        return new Faker(locale).address().streetAddress();
    }

    public static String getCity(Locale locale) {
        return new Faker(locale).address().cityName();
    }

    public static String getZipCode(Locale locale) {
        return new Faker(locale).address().zipCode();
    }
}
