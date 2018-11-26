package utils.data;

import java.util.Objects;

public class RateInfo {
    private String cancellationPolicy;
    private String price;
    private String roomName;

    public RateInfo(String cancellationPolicy, String price, String roomName) {
        this.cancellationPolicy = cancellationPolicy;
        this.price = price;
        this.roomName = roomName;
    }

    public RateInfo() {
    }

    @Override
    public String toString() {
        return "RateInfo{" +
                "cancellationPolicy='" + cancellationPolicy + '\'' +
                ", price='" + price + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RateInfo)) return false;
        RateInfo rateInfo = (RateInfo) o;
        return Objects.equals(getCancellationPolicy(), rateInfo.getCancellationPolicy()) &&
                Objects.equals(getPrice(), rateInfo.getPrice()) &&
                Objects.equals(getRoomName(), rateInfo.getRoomName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCancellationPolicy(), getPrice(), getRoomName());
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
