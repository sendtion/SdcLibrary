package com.fb.sdclibrary.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import com.fb.sdclibrary.activity.PickerActivity;

/**
 * 在Android P上就会抛出Uninitialized ActivityThread, likely app-created Instrumentation的异常，显然这是因为EvilInstrumentation的mThread为空导致的。
 * 想要解决这个问题，就必须重写EvilInstrumentation中的newActivity方法
 */
public class InstrumentationProxy extends Instrumentation {

    private static final String TAG = "InstrumentationProxy";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public InstrumentationProxy(Instrumentation base) {
        mBase = base;
    }

    public Activity newActivity(ClassLoader cl, String className,
                                Intent intent)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        try {
            //这里需要setExtrasClassLoader 不然的话，getParecleable 对象可能会拿不到
            //很多hook Instrumentation的人都不知道。
            // 这里try catch 是防止恶意攻击  导致android.os.BadParcelableException: ClassNotFoundException when unmarshalling
            intent.setExtrasClassLoader(cl);
            intent.getBooleanExtra("a",false);
        }catch (Exception e){

        }
        if (intent.getBooleanExtra("PickerActivity",false)) {
            return super.newActivity(cl, PickerActivity.class.getName(), intent);
        }
        return mBase.newActivity(cl, className, intent);
    }
}
