package com.example.lab.mylifelogger;

/**
 * Created by lab on 16. 12. 17.
 */

public class ListItem {
    private String time;
    private String lat;
    private String lgt;

    public ListItem(String time, String lat, String lgt) {
        this.time = time;
        this.lgt = lgt;
        this.lat = lat;
    }
    public String getTime() {
        return time;
    }
    public String getLat() {
        return lat;
    }
    public String getLgt() {
        return lgt;
    }
}

