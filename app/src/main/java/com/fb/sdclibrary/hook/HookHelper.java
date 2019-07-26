package com.fb.sdclibrary.hook;

import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过Hook实现自定义Instrumentation，进而可以控制启动的Activity（newActivity）
 */
public class HookHelper {

    public static void init(){
        try {
            // 先获取到当前的ActivityThread对象
            Object currentActivityThread = RefInvoke.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread");
            // 拿到原始的 mInstrumentation字段
            Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getFieldObject(currentActivityThread, "mInstrumentation");
            // 创建代理对象
            Instrumentation evilInstrumentation = new InstrumentationProxy(mInstrumentation);
            // 偷梁换柱
            RefInvoke.setFieldObject(currentActivityThread, "mInstrumentation", evilInstrumentation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookInstrumentation() {
        Class<?> activityThread = null;
        try {
            activityThread = Class.forName("android.app.ActivityThread");
            Method sCurrentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            //获取ActivityThread 对象
            Object activityThreadObject = sCurrentActivityThread.invoke(activityThread);

            //获取 Instrumentation 对象
            Field mInstrumentation = activityThread.getDeclaredField("mInstrumentation");
            mInstrumentation.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
            InstrumentationProxy customInstrumentation = new InstrumentationProxy(instrumentation);
            //将我们的 customInstrumentation 设置进去
            mInstrumentation.set(activityThreadObject, customInstrumentation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
