package com.fb.sdclibrary.activity;

import android.os.Bundle;

import com.fb.sdclibrary.R;
import com.skyfishjy.library.RippleBackground;

import butterknife.BindView;

public class RippleSignActivity extends BaseActivity {

    @BindView(R.id.ripple_sign)
    RippleBackground rippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_ripple_sign);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        rippleBackground.startRippleAnimation();
    }

    @Override
    public void onPause() {
        super.onPause();
        rippleBackground.stopRippleAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (rippleBackground != null && rippleBackground.isRippleAnimationRunning()){
            rippleBackground.stopRippleAnimation();
        }
    }
}
