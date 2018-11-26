package magnitude.pages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Pages {

    @SerializedName("resolutions")
    @Expose
    private List<Resolution> resolutions = null;
    @SerializedName("hotelPages")
    @Expose
    private List<HotelPage> hotelPages = null;
    @SerializedName("languages")
    @Expose
    private List<Integer> languages = null;
    @SerializedName("hotelBlocks")
    @Expose
    private List<String> hotelBlocks = null;
    @SerializedName("compare")
    @Expose
    private boolean compare;
    @SerializedName("source")
    @Expose
    private String source;

    /**
     * No args constructor for use in serialization
     *
     */
    public Pages() {
    }

    /**
     *
     * @param languages
     * @param source
     * @param hotelPages
     * @param compare
     * @param hotelBlocks
     * @param resolutions
     */
    public Pages(List<Resolution> resolutions, List<HotelPage> hotelPages, List<Integer> languages, List<String> hotelBlocks, boolean compare, String source) {
        super();
        this.resolutions = resolutions;
        this.hotelPages = hotelPages;
        this.languages = languages;
        this.hotelBlocks = hotelBlocks;
        this.compare = compare;
        this.source = source;
    }

    public List<Resolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    public List<HotelPage> getHotelPages() {
        return hotelPages;
    }

    public void setHotelPages(List<HotelPage> hotelPages) {
        this.hotelPages = hotelPages;
    }

    public List<Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Integer> languages) {
        this.languages = languages;
    }

    public List<String> getHotelBlocks() {
        return hotelBlocks;
    }

    public void setHotelBlocks(List<String> hotelBlocks) {
        this.hotelBlocks = hotelBlocks;
    }

    public boolean isCompare() {
        return compare;
    }

    public void setCompare(boolean compare) {
        this.compare = compare;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("resolutions", resolutions).append("hotelPages", hotelPages).append("languages", languages).append("hotelBlocks", hotelBlocks).append("compare", compare).append("source", source).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(languages).append(source).append(hotelPages).append(compare).append(hotelBlocks).append(resolutions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pages) == false) {
            return false;
        }
        Pages rhs = ((Pages) other);
        return new EqualsBuilder().append(languages, rhs.languages).append(source, rhs.source).append(hotelPages, rhs.hotelPages).append(compare, rhs.compare).append(hotelBlocks, rhs.hotelBlocks).append(resolutions, rhs.resolutions).isEquals();
    }

}