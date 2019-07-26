package com.fb.sdclibrary.permission;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;


public class GPermission {
    private static final String MARK = Build.MANUFACTURER.toLowerCase();
    static Map<String, String> permissionMap;
    static Map<String, String> permissionTips;
//    private Map<String, PermissionCallback> call = new HashMap<>();
//    private Queue<String[]> permissions = new LinkedList<>();
    private static GPermission permission;
//    private boolean isRequest = false;
    private PermissionCallback callback;

    public static GPermission getPermission() {
        if (permission == null) {
            synchronized (GPermission.class) {
                if (permission == null) {
                    permission = new GPermission();
                    permission.initMap();
                    permission.initTips();
                }
            }
        }
        return permission;
    }

    public void initMap() {
        permissionMap = new HashMap<>();
        permissionMap.put(PermissionCode.REQUEST_INSTALL_PACKAGES, PermissionCode.REQUEST_INSTALL_PACKAGES_STRING);
        permissionMap.put(PermissionCode.SYSTEM_ALERT_WINDOW, PermissionCode.SYSTEM_ALERT_WINDOW_STRING);
        permissionMap.put(PermissionCode.READ_CALENDAR, PermissionCode.READ_CALENDAR_STRING);
        permissionMap.put(PermissionCode.WRITE_CALENDAR, PermissionCode.WRITE_CALENDAR_STRING);
        permissionMap.put(PermissionCode.CAMERA, PermissionCode.CAMERA_STRING);
        permissionMap.put(PermissionCode.READ_CONTACTS, PermissionCode.READ_CONTACTS_STRING);
        permissionMap.put(PermissionCode.WRITE_CONTACTS, PermissionCode.WRITE_CONTACTS_STRING);
        permissionMap.put(PermissionCode.GET_ACCOUNTS, PermissionCode.GET_ACCOUNTS_STRING);
        permissionMap.put(PermissionCode.ACCESS_FINE_LOCATION, PermissionCode.ACCESS_FINE_LOCATION_STRING);
        permissionMap.put(PermissionCode.ACCESS_COARSE_LOCATION, PermissionCode.ACCESS_COARSE_LOCATION_STRING);
        permissionMap.put(PermissionCode.RECORD_AUDIO, PermissionCode.RECORD_AUDIO_STRING);
        permissionMap.put(PermissionCode.READ_PHONE_STATE, PermissionCode.READ_PHONE_STATE_STRING);
        permissionMap.put(PermissionCode.CALL_PHONE, PermissionCode.CALL_PHONE_STRING);
        permissionMap.put(PermissionCode.READ_CALL_LOG, PermissionCode.READ_CALL_LOG_STRING);
        permissionMap.put(PermissionCode.WRITE_CALL_LOG, PermissionCode.WRITE_CALL_LOG_STRING);
        permissionMap.put(PermissionCode.ADD_VOICEMAIL, PermissionCode.ADD_VOICEMAIL_STRING);
        permissionMap.put(PermissionCode.USE_SIP, PermissionCode.USE_SIP_STRING);
        permissionMap.put(PermissionCode.PROCESS_OUTGOING_CALLS, PermissionCode.PROCESS_OUTGOING_CALLS_STRING);
        permissionMap.put(PermissionCode.ANSWER_PHONE_CALLS, PermissionCode.ANSWER_PHONE_CALLS_STRING);
        permissionMap.put(PermissionCode.READ_PHONE_NUMBERS, PermissionCode.READ_PHONE_NUMBERS_STRING);
        permissionMap.put(PermissionCode.BODY_SENSORS, PermissionCode.BODY_SENSORS_STRING);
        permissionMap.put(PermissionCode.SEND_SMS, PermissionCode.SEND_SMS_STRING);
        permissionMap.put(PermissionCode.RECEIVE_SMS, PermissionCode.RECEIVE_SMS_STRING);
        permissionMap.put(PermissionCode.READ_SMS, PermissionCode.READ_SMS_STRING);
        permissionMap.put(PermissionCode.RECEIVE_WAP_PUSH, PermissionCode.RECEIVE_WAP_PUSH_STRING);
        permissionMap.put(PermissionCode.RECEIVE_MMS, PermissionCode.RECEIVE_MMS_STRING);
        permissionMap.put(PermissionCode.READ_EXTERNAL_STORAGE, PermissionCode.READ_EXTERNAL_STORAGE_STRING);
        permissionMap.put(PermissionCode.WRITE_EXTERNAL_STORAGE, PermissionCode.WRITE_EXTERNAL_STORAGE_STRING);
    }

    public void initTips() {
        permissionTips = new HashMap<>();
        permissionTips.put(PermissionCode.CAMERA, PermissionCode.CAMERA_TIP);
        permissionTips.put(PermissionCode.ACCESS_FINE_LOCATION, PermissionCode.ACCESS_FINE_LOCATION_TIP);
        permissionTips.put(PermissionCode.ACCESS_COARSE_LOCATION, PermissionCode.ACCESS_COARSE_LOCATION_TIP);
        permissionTips.put(PermissionCode.READ_PHONE_STATE, PermissionCode.READ_PHONE_STATE_TIP);
        permissionTips.put(PermissionCode.CALL_PHONE, PermissionCode.CALL_PHONE_TIP);
        permissionTips.put(PermissionCode.READ_EXTERNAL_STORAGE, PermissionCode.READ_EXTERNAL_STORAGE_TIP);
        permissionTips.put(PermissionCode.WRITE_EXTERNAL_STORAGE, PermissionCode.WRITE_EXTERNAL_STORAGE_TIP);
    }

    public synchronized void request(Context context, String[] permissions, PermissionCallback callback) {
        this.callback = callback;
        if (permissions != null && permissions.length > 0) {
            PermissionActivity.request(context, permissions, callback);
        }else {
            callback.onPermissionGranted();
        }
    }

    public synchronized void request(Context context, String[] permissions) {
        if (permissions != null && permissions.length > 0) {
            PermissionActivity.request(context, permissions, callback);
        }else {
            callback.onPermissionGranted();
        }
    }

    public boolean hasPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Jump to Settings page of your application
     */
    public static void startSettingsActivity(Context context) {
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用权限设置页面
     *
     * @param context 上下文对象
     * @param newTask 是否使用新的任务栈启动
     */
    static void startSettings(Context context, boolean newTask) {

        Intent intent = null;
        if (MARK.contains("huawei")) {
            intent = huawei(context);
        } else if (MARK.contains("xiaomi")) {
            intent = xiaomi(context);
        } else if (MARK.contains("oppo")) {
            intent = oppo(context);
        } else if (MARK.contains("vivo")) {
            intent = vivo(context);
        } else if (MARK.contains("meizu")) {
            intent = meizu(context);
        }

        if (intent == null || !hasIntent(context, intent)) {
            intent = google(context);
        }

        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            context.startActivity(intent);
        } catch (Exception ignored) {
            intent = google(context);
            context.startActivity(intent);
        }
    }

    private static Intent google(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return intent;
    }

    private static Intent huawei(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
        if (hasIntent(context, intent)) return intent;
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
        if (hasIntent(context, intent)) return intent;
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
        return intent;
    }

    private static Intent xiaomi(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (hasIntent(context, intent)) return intent;

        intent.setPackage("com.miui.securitycenter");
        if (hasIntent(context, intent)) return intent;

        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        if (hasIntent(context, intent)) return intent;

        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        return intent;
    }

    private static Intent oppo(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
        if (hasIntent(context, intent)) return intent;

        intent.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
        if (hasIntent(context, intent)) return intent;

        intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.PermissionAppListActivity");
        return intent;
    }

    private static Intent vivo(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager");
        intent.putExtra("packagename", context.getPackageName());
        if (hasIntent(context, intent)) return intent;

        intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"));
        return intent;
    }

    private static Intent meizu(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.putExtra("packageName", context.getPackageName());
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
        return intent;
    }

    private static boolean hasIntent(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).isEmpty();
    }
}
