package com.example.yunseung_u.toex.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.yunseung_u.toex.Application.GlobalApplication;
import com.example.yunseung_u.toex.model.UserInfo;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import static android.support.constraint.Constraints.TAG;

public class KakaotalkSignupActivity extends Activity{


    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestMe();
    }

    private void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e(TAG, "error message=" + errorResult);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if(result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                }else{
                    redirectLoginActivity();
                }

//                super.onFailure(errorResult);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {

                Log.d(TAG, "onSessionClosed1 =" + errorResult);
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                //카카오톡 회원이 아닐시
                
                showSignup();
                Log.d(TAG, "onNotSignedUp ");

            }

            @Override
            public void onSuccess(UserProfile result) {
                Log.e("UserProfile", result.toString());
                Log.e("UserProfile", result.getId() + "");
                UserInfo userInfo = new UserInfo();
                userInfo.setKakao_id(result.getNickname());
                userInfo.setId(result.getUUID());
                userInfo.setKakao_profile(result.getThumbnailImagePath());
                GlobalApplication.setCurrentUser(userInfo);
                redirectMainActivity();
            }
        });
    }

    private void showSignup() {
        redirectLoginActivity();
    }

    private void redirectMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}

