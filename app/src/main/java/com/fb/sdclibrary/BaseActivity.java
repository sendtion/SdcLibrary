package com.fb.sdclibrary;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fb.sdclibrary.utils.AppManager;
import com.fb.sdclibrary.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState, int resourceId) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(resourceId);
        // 添加Activity到堆栈
        //AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        if (isBindEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initStatusBar();
        initView();
        initData();
        loadData();
    }

//    @Override
//    public void setRequestedOrientation(int requestedOrientation) {
//        return;
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//    }


    protected void initStatusBar() {
//        if (Build.BRAND.equalsIgnoreCase("meizu") || Build.BRAND.equalsIgnoreCase("xiaomi")) {
//            StatusBarUtils.setColor(this, Color.parseColor("#FFFFFF"), 0);
//            StatusBarUtils.StatusBarLightMode(this);
//        } else {
//            StatusBarUtils.setColor(this, Color.parseColor("#FFFFFF"), 50);
//        }

        StatusBarUtil.addStatusBarView(this, Color.WHITE);
        StatusBarUtil.setStatusBar(this, false, false);
        StatusBarUtil.setStatusTextColor(true, this);
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void loadData();

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
        System.gc();
    }

    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //MobclickAgent.onPause(this);

    }
}
