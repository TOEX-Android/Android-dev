package com.example.yunseung_u.toex.view.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.yunseung_u.toex.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class MainActivity extends AppCompatActivity {


    CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);



        dialog = new CustomDialog(this);

        dialog.setContentView(R.layout.activity_custom_dialog);
        dialog.setDialogListener(new CustomDialog.MyDialogListener() {
            @Override
            public void onPositiveClicked(String minNum, String maxNum) {
                setResult(minNum,maxNum);
            }

            @Override
            public void onNegativeClicked() {

            }
        });

        dialog.getWindow().setGravity(Gravity.CENTER);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

        params.width = 1000;
        params.height = 800;

        dialog.getWindow().setAttributes(params);

    }

    private void setResult(String minNum, String maxNum) {

        Log.d("SetResult: ",minNum.toString() + "," + maxNum.toString());
    }


    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.d("FUCK", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

    @OnClick(R.id.value_setting)
    public void onValueBtn(){

        dialog.show();
    }
    @OnClick(R.id.distance_setting)
    public void onDistanceBtn(){
        Log.d("Fuck","You");
    }

}
