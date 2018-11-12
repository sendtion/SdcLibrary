package com.fb.sdclibrary.activity;

import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fb.sdclibrary.R;
import com.fb.sdclibrary.utils.AppManager;
import com.fb.sdclibrary.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;


public abstract class BaseActivity extends AppCompatActivity {

    public void setContentView(int layoutId) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.setContentView(layoutId);

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        // 初始化ButterKnife
        ButterKnife.bind(this);
        // 初始化EventBus
        if (isBindEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        // 初始化状态栏
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

        StatusBarUtil.addStatusBarView(this, ContextCompat.getColor(this, R.color.colorPrimary));
        StatusBarUtil.setStatusBar(this, false, false);
        StatusBarUtil.setStatusTextColor(false, this); //状态栏颜色
    }

    protected abstract void initView();

    protected void initData() {
    }

    protected void loadData() {
    }

    public void showToast(String text){
        Toasty.normal(this, text).show();
    }

    public void showShortToast(String text){
        Toasty.normal(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String text){
        Toasty.normal(this, text, Toast.LENGTH_LONG).show();
    }

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
