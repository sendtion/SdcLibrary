package com.fb.sdclibrary.activity;

import android.os.Bundle;

import com.fb.sdclibrary.R;

public class RippleViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_view);

        //水波扩散效果：https://blog.csdn.net/zhuwentao2150/article/details/79452735
    }

    @Override
    protected void initView() {

    }
}
