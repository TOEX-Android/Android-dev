package com.example.yunseung_u.toex.Application;

import android.app.Activity;
import android.app.Application;

import com.example.yunseung_u.toex.model.UserInfo;
import com.example.yunseung_u.toex.view.adapter.KakaoSDKAdapter;
import com.kakao.auth.KakaoSDK;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class GlobalApplication extends Application {



    private static Retrofit retrofit = null;
    private static volatile GlobalApplication obj = null;
    private static volatile Activity currentActivity = null;
    private static UserInfo currentUser = new UserInfo();



    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    public static GlobalApplication getGlobalApplicationContext() {
        return obj;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }


    public static Retrofit getRetrofit(String baseURL)
    {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        obj = null;
    }

    public static UserInfo getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserInfo userInfo) {
        GlobalApplication.currentUser = userInfo;
    }
}