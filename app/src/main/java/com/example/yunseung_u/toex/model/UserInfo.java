package com.example.yunseung_u.toex.model;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("kakao_id")
    private String kakao_id;
    @SerializedName("kakao_profile")
    private String kakao_profile;

    public String getId(){return id;}
    public String getKakao_id(){return kakao_id;}
    public String getKakao_profile(){return kakao_profile;}

    public void setId(String id){
        this.id = id;
    }
    public void setKakao_id(String kakao_id){
        this.kakao_id = kakao_id;
    }
    public void setKakao_profile(String kakao_profile){
        this.kakao_profile = kakao_profile;
    }

}
