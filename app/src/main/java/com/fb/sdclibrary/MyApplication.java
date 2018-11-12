package com.fb.sdclibrary;


import android.app.Application;

import com.fb.sdclibrary.utils.Utils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
    }
}
