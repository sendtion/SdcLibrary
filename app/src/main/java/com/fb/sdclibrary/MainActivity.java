package com.fb.sdclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    @OnClick({R.id.tv_water_ripple_view})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_water_ripple_view:
                Intent intent = new Intent();
                intent.setClass(this, RippleViewActivity.class);
                startActivity(intent);
                break;
        }
    }

}
