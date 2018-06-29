package com.example.yunseung_u.toex.Service.Network.API;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/dealslist/")
    Call<JsonArray> getDealslist();

    @GET("/dealslist/{id}/")
    Call<JsonObject> getDeal(@Path("id") String id);

    @POST("/dealslist/")
    Call<JsonObject> addDeal(@Field("id") String id, @Field("place") String place, @Field("latitude") String latitude
            , @Field("longitude") String longitude, @Field("sellprice") int sellprice, @Field("sellunit") String sellunit,
                             @Field("buyprice") int buyprice, @Field("buyunit") String buyunit);

    @GET("/userinfo/")
    Call<JsonArray> getUserInfoList();

    @GET("/userinfo/{id}/")
    Call<JsonObject> getUserInfo(@Path("id") String id);

    @POST("/userinfo/")
    Call<JsonObject> addUserInfo(@Field("id") String id, @Field("kakao_id") String kakao_id,
                                 @Field("kakao_profile") String kakao_profile);
}
