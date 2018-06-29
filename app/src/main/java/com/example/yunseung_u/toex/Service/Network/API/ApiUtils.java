package com.example.yunseung_u.toex.Service.Network.API;

import com.example.yunseung_u.toex.Application.RetrofitClient;

public class ApiUtils {
    public static final String BASE_URL = "http://121.189.179.92:8000";
    public static ApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
