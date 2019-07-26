package com.fb.sdclibrary.utils;


import android.app.Activity;
import android.app.AppOpsManager;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.fb.sdclibrary.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class NotificationUtils {

    private static final String CHANNEL_ID = "channel_002";
    private static final String GROUP_ID = "group_002";
    private static int notifyId = 1;
    private static final String TAG_NOFIFY = "fb_notify";

    private static final String CHECK_OP_NO_THROW    = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    //判断该app是否打开了通知

    /**
     * 可以通过NotificationManagerCompat 中的 areNotificationsEnabled()来判断是否开启通知权限。NotificationManagerCompat 在 android.support.v4.app包中，是API 22.1.0 中加入的。而 areNotificationsEnabled()则是在 API 24.1.0之后加入的。
     * areNotificationsEnabled 只对 API 19 及以上版本有效，低于API 19 会一直返回true
     */
    public static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            return notificationManagerCompat.areNotificationsEnabled();
        }else {
            return true;
        }
    }
    //打开手机设置页面

    /**
     * 假设没有开启通知权限，点击之后就需要跳转到 APP的通知设置界面，对应的Action是：Settings.ACTION_APP_NOTIFICATION_SETTINGS, 这个Action是 API 26 后增加的
     * 如果在部分手机中无法精确的跳转到 APP对应的通知设置界面，那么我们就考虑直接跳转到 APP信息界面，对应的Action是：Settings.ACTION_APPLICATION_DETAILS_SETTINGS
     */
    public static void gotoSet(Context context) {

        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, CHANNEL_ID);
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        }else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void createChannel(Context context) {
        NotificationManager mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT && mManager != null) {
            //分组（可选）
            //groupId要唯一
            NotificationChannelGroup group = new NotificationChannelGroup(GROUP_ID, "智慧安全家");
//                创建group
            mManager.createNotificationChannelGroup(group);

            //channelId要唯一
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "报警推送",
                    NotificationManager.IMPORTANCE_DEFAULT);
            // 开启指示灯，如果设备有的话
            channel.enableLights(true);
            // 设置指示灯颜色
            channel.setLightColor(ContextCompat.getColor(context, R.color.color_38a2ef));
            // 是否在久按桌面图标时显示此渠道的通知
            channel.setShowBadge(true);
            // 设置是否应在锁定屏幕上显示此频道的通知
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PRIVATE);
            // 设置绕过免打扰模式
            channel.setBypassDnd(true);
            channel.setGroup(GROUP_ID);
            mManager.createNotificationChannel(channel);
        }
    }

    public static void sendMessage(String message, Context context){
//        NotificationManager mManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        if (mManager != null) {
//            if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
//                Intent intentClick = new Intent(context, MyReceiver.class);
//                intentClick.setAction(JPushInterface.ACTION_NOTIFICATION_OPENED);
//                intentClick.putExtras(new Bundle());
//                PendingIntent pendingIntentClick = PendingIntent.getBroadcast(context, 0,
//                        intentClick, PendingIntent.FLAG_ONE_SHOT);
//                //创建通知时，标记你的渠道id
//                Notification notification = new Notification.Builder(context, NotificationUtils.CHANNEL_ID)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setTicker(message)
//                        .setWhen(System.currentTimeMillis())
//                        .setContentTitle("您有一条新的报警信息，请及时处理")
//                        .setContentText(message)
//                        .setAutoCancel(true)
//                        .setContentIntent(pendingIntentClick)
//                        .build();
//                mManager.notify(TAG_NOFIFY, notifyId++, notification);
//            } else {
//                Intent intentClick = new Intent(context, MyReceiver.class);
//                intentClick.setAction(JPushInterface.ACTION_NOTIFICATION_OPENED);
//                intentClick.putExtras(new Bundle());
//                PendingIntent pendingIntentClick = PendingIntent.getBroadcast(context, 0,
//                        intentClick, PendingIntent.FLAG_ONE_SHOT);
//                Notification notification = new Notification.Builder(context)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setTicker(message)
//                        .setWhen(System.currentTimeMillis())
//                        .setContentTitle("您有一条新的报警信息，请及时处理")
//                        .setContentText(message)
//                        .setAutoCancel(true)
//                        .setContentIntent(pendingIntentClick)
//                        .build();
//                mManager.notify(TAG_NOFIFY, notifyId++, notification);
//            }
//        }
    }

    /**
     * 判断通知开关是否打开
     * https://blog.csdn.net/zhangyuehuan/article/details/82872459
     */
    public static boolean isNotificationOpen(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return isEnableV26(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return isEnableV19(context);
        } else {
            return true;
        }
    }

    /**
     * Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
     * 19及以上
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isEnableV19(Context context) {
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        Class appOpsClass;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
     * 针对8.0及以上设备
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isEnableV26(Context context) {
        try {
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            Method sServiceField = notificationManager.getClass().getDeclaredMethod("getService");
            sServiceField.setAccessible(true);
            Object sService = sServiceField.invoke(notificationManager);

            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;

            Method method = sService.getClass().getDeclaredMethod("areNotificationsEnabledForPackage"
                    , String.class, Integer.TYPE);
            method.setAccessible(true);
            return (boolean) method.invoke(sService, pkg, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void gotoNotificationSetting(Activity activity) {
        ApplicationInfo appInfo = activity.getApplicationInfo();
        String pkg = activity.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        try {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, CHANNEL_ID);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra("app_package", pkg);
                intent.putExtra("app_uid", uid);
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
            } else {
                intent.setAction(Settings.ACTION_SETTINGS);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
            }
            activity.startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            activity.startActivity(intent);
        }
    }

}
