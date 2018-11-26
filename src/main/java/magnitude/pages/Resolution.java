package magnitude.pages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Resolution {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;

    /**
     * No args constructor for use in serialization
     *
     */
    public Resolution() {
    }

    /**
     *
     * @param height
     * @param width
     * @param name
     */
    public Resolution(String name, int width, int height) {
        super();
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("width", width).append("height", height).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(height).append(width).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Resolution) == false) {
            return false;
        }
        Resolution rhs = ((Resolution) other);
        return new EqualsBuilder().append(height, rhs.height).append(width, rhs.width).append(name, rhs.name).isEquals();
    }

}
