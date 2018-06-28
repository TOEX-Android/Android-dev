package com.example.yunseung_u.toex.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

public class Transaction implements Serializable{

    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("value")
    private String value;
    @SerializedName("location")
    private HashMap<String,String> location;
    @SerializedName("tag")
    private String tag;

    public Transaction(String from, String to, String value, HashMap<String, String> location, String tag) {
        this.from = from;
        this.to = to;
        this.value = value;
        this.location = location;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
