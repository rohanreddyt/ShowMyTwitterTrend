
package com.example.rohan.showmytwittertrend.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trend {

    @SerializedName("trends")
    @Expose
    private List<Trend_> trends = null;
    @SerializedName("as_of")
    @Expose
    private String asOf;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;

    public List<Trend_> getTrends() {
        return trends;
    }

    public void setTrends(List<Trend_> trends) {
        this.trends = trends;
    }

    public String getAsOf() {
        return asOf;
    }

    public void setAsOf(String asOf) {
        this.asOf = asOf;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

}
