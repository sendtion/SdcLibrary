package com.fb.sdclibrary;


import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.fb.sdclibrary.utils.Utils;

import io.github.isliqian.NiceEmail;

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        NiceEmail.config(NiceEmail.SMTP_QQ(), "51103942@qq.com", "jtmoybnwknrnbjha");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决方法64k限制
        MultiDex.install(this);
    }
}
