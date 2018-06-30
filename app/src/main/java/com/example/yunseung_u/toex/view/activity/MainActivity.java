package com.example.yunseung_u.toex.view.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.yunseung_u.toex.R;
import com.example.yunseung_u.toex.model.DealItem;
import com.example.yunseung_u.toex.view.adapter.DealitemAdapter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

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



        ArrayList items = new ArrayList<>();

        items.add(new DealItem("asw","ayqwer","34","124",400,
                "JPY",1020,"WON","asdfsdf","100"));

        items.add(new DealItem("asw","ayqwer","34","124",400,
                "JPY",1020,"WON","asdfsdf","100"));

        items.add(new DealItem("asw","ayqwer","34","124",400,
                "JPY",1020,"WON","asdfsdf","100"));

        items.add(new DealItem("asw","ayqwer","34","124",400,
                "JPY",1020,"WON","asdfsdf","100"));

        items.add(new DealItem("asw","ayqwer","34","124",400,
                "JPY",1020,"WON","asdfsdf","100"));






        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DealitemAdapter mAdapter = new DealitemAdapter(this,items);
        recyclerView.setAdapter(mAdapter);


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
