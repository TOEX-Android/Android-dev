package com.example.yunseung_u.toex.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appyvet.materialrangebar.RangeBar;
import com.example.yunseung_u.toex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialog extends Dialog {


    @BindView(R.id.minMarkerNum) TextView minMarkerNumText;
    @BindView(R.id.maxMarkerNum) TextView maxMarkerNumText;
    @BindView(R.id.minNum) TextView minNumText;
    @BindView(R.id.maxNum) TextView maxNumText;
    @BindView(R.id.rangeBar) RangeBar rangebar;


    public interface MyDialogListener {
        public void onPositiveClicked(String minNum,String maxNum);
        public void onNegativeClicked();
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    private MyDialogListener dialogListener;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        ButterKnife.bind(this);


        rangebar.setTickStart(1);
        rangebar.setTickInterval(1);
        rangebar.setTickEnd(100);
        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex, String leftPinValue, String rightPinValue) {

                minMarkerNumText.setText("" + leftPinIndex);
                maxMarkerNumText.setText("" + rightPinIndex);

            }

        });
    }

    @OnClick(R.id.back_btn)
    public void submitBtn(){

        String min = minMarkerNumText.getText().toString();
        String max = maxMarkerNumText.getText().toString();
        dialogListener.onPositiveClicked(min,max);
        dismiss();
    }

}
