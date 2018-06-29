package com.example.yunseung_u.toex.model;

import android.media.Image;

import java.io.Serializable;
import java.util.HashMap;

public class TransactionItem implements Serializable{

    private Image userProfileURL;
    private String from;
    private String to;
    private String value;
    private HashMap<String,String> location;
    private String currency;

    public TransactionItem(Image userProfileURL, String from, String to, String value, HashMap<String, String> location, String currency) {
        this.userProfileURL = userProfileURL;
        this.from = from;
        this.to = to;
        this.value = value;
        this.location = location;
        this.currency = currency;
    }

    public Image getUserProfileURL(){
        return userProfileURL;
    }

    public void setUserprofileURL(Image userprofile_icon) {
        this.userProfileURL = userprofile_icon;

    }
        public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HashMap<String, String> getLocation() {
        return location;
    }

    public void setLocation(HashMap<String, String> location) {
        this.location = location;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
