package utils.data;

public class WireCardTestCard {
    private String cardType;
    private String cardNumber;
    private String expiredDate;
    private String cvvCode;
    private String errorCode;
    private String errorMessage;

    public WireCardTestCard(String cardType, String cardNumber, String expiredDate, String cvvCode, String errorCode, String errorMessage) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.cvvCode = cvvCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
