package com.fb.sdclibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fb.sdclibrary.R;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);
        //setContentView(R.layout.activity_main);

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.tv_water_ripple_view, R.id.tv_water_ripple_sign, R.id.tv_date_picker})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_water_ripple_view:
                intent.setClass(this, RippleViewActivity.class);
                break;
            case R.id.tv_water_ripple_sign:
                intent.setClass(this, RippleSignActivity.class);
                break;
            case R.id.tv_date_picker:
                intent.setClass(this, PickerActivity.class);
                break;
        }
        startActivity(intent);
    }

}
