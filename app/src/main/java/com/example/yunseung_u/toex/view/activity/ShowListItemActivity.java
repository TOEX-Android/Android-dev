package com.example.yunseung_u.toex.view.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yunseung_u.toex.R;
import com.example.yunseung_u.toex.model.DealItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShowListItemActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Item via Intent
    DealItem item;

    ImageView userProfileImageView;
    TextView userAddressTextView;
    TextView userIdTextView;
    TextView sellTextView;
    TextView buyTextView;

    Button chatButton;



    //User 이미지 사진 다운로드 및 Chat 버튼 눌렀을 때 이벤트 정의 필요!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_listitem);



        Intent intent = getIntent();
        //item = (DealItem)intent.getSerializableExtra("item");

        //Profile Image -> Circle
        userProfileImageView = (ImageView)findViewById(R.id.userProfileImageView);
        userProfileImageView.setBackground(new ShapeDrawable(new OvalShape()));
        userProfileImageView.setClipToOutline(true);

        //UserId
        userIdTextView = (TextView)findViewById(R.id.userID);
        userIdTextView.setText(item.getId());

        //Google Map
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //UserAddress
        userAddressTextView = (TextView)findViewById(R.id.userPlace);
        //Get address from latitude and longitude
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try{
            addresses = geocoder.getFromLocation(Float.valueOf(item.getLatitude()), Float.valueOf(item.getLongitude()), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            userAddressTextView.setText(address);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        sellTextView = (TextView)findViewById(R.id.sellPriceTxv);
        StringBuilder sellPrice = new StringBuilder(String.valueOf(item.getSellprice()));
        if(sellPrice.length() > 3){
            int cur = sellPrice.length()-3;
            while(cur >= 0){
                sellPrice.insert(cur,',');
                cur -= 3;
            }
        }
        sellTextView.setText(sellPrice.toString());

        String sellUnit = item.getSellunit();
        if(sellUnit.equals("USD")){
            sellTextView.append(getString(R.string.USD));
        }else if(sellUnit.equals("KRW")){
            sellTextView.append("￦");
        }else if(sellUnit.equals("JPY")){
            sellTextView.append(getString(R.string.JPY));
        }else if(sellUnit.equals("EUR")){
            sellTextView.append("€");
        }

        buyTextView = (TextView)findViewById(R.id.buyPriceTxv);
        StringBuilder buyPrice = new StringBuilder(String.valueOf(item.getBuyprice()));
        if(buyPrice.length() > 3){
            int cur = buyPrice.length()-3;
            while(cur >= 0){
                buyPrice.insert(cur,',');
                cur -= 3;
            }
        }
        buyTextView.setText(buyPrice.toString());

        String buyUnit = item.getBuyunit();
        if(buyUnit.equals("USD")){
            buyTextView.append(getString(R.string.USD));
        }else if(buyUnit.equals("KRW")){
            buyTextView.append("￦");
        }else if(buyUnit.equals("JPY")){
            buyTextView.append(getString(R.string.JPY));
        }else if(buyUnit.equals("EUR")){
            buyTextView.append("€");
        }

        chatButton.findViewById(R.id.goChatRoomBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        //initialize location according to item instance
        LatLng place = new LatLng(Float.valueOf(item.getLatitude()),Float.valueOf(item.getLongitude()));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place);
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLng(place));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }

}
