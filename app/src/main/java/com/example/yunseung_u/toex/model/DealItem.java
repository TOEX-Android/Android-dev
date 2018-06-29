package com.example.yunseung_u.toex.model;

import com.google.gson.annotations.SerializedName;

public class DealItem {

    @SerializedName("id")
    private String id;
    @SerializedName("place")
    private String place;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("sellprice")
    private int sellprice;
    @SerializedName("sellunit")
    private String sellunit;
    @SerializedName("buyprice")
    private int buyprice;
    @SerializedName("buyunit")
    private String buyunit;

    public String getId(){return id;}
    public String getPlace(){return place;}
    public String getLatitude(){return latitude;}
    public String getLongitude(){return longitude;}
    public int getSellprice(){return sellprice;}
    public String getSellunit(){return sellunit;}
    public int getBuyprice(){return buyprice;}
    public String getBuyunit(){return buyunit;}

    public void setId(String id){
        this.id = id;
    }
    public void setPlace(String place){
        this.place = place;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    public void setSellprice(int sellprice){
        this.sellprice = sellprice;
    }
    public void setSellunit(String sellunit){
        this.sellunit = sellunit;
    }
    public void setBuyprice(int buyprice){
        this.buyprice = buyprice;
    }
    public void setBuyunit(String buyunit){
        this.buyunit = buyunit;
    }
}
