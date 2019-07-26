package com.fb.sdclibrary.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;

import com.fb.sdclibrary.utils.SPUtils;
import com.fb.sdclibrary.utils.Utils;
import com.fb.sdclibrary.widget.CustomDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PermissionActivity extends Activity {
    public static final String KEY_PERMISSIONS = "permissions";
    public static final String KEY_PERMISSIONS_ADDRESS = "permissions_address";
    private static final int RC_REQUEST_PERMISSION = 100;
    private static Map<String, PermissionCallback> call = new HashMap<>();
    private PermissionCallback CALLBACK;
    private CustomDialog customDialog;

    public static void request(Context context, String[] permissions, PermissionCallback callback) {
        call.put(permissions.toString(), callback);
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putStringArrayListExtra(KEY_PERMISSIONS, new ArrayList<>(Arrays.asList(permissions)));
        intent.putExtra(KEY_PERMISSIONS_ADDRESS, permissions.toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (!intent.hasExtra(KEY_PERMISSIONS) || !intent.hasExtra(KEY_PERMISSIONS_ADDRESS)) {
            return;
        }
        try {
            ArrayList<String> tempPermissions = getIntent().getStringArrayListExtra(KEY_PERMISSIONS);
            String address = getIntent().getStringExtra(KEY_PERMISSIONS_ADDRESS);
            CALLBACK = call.get(address);
            call.remove(address);
            List<String> permissions = new ArrayList<>();
            for (int i = 0; tempPermissions != null && i < tempPermissions.size(); i++) {
                String permission = tempPermissions.get(i);
                if (permission != null && !GPermission.getPermission().hasPermission(Utils.getApp(), permission)) {
                    permissions.add(permission);
                }
            }
            if (permissions.size() <= 0) {
                CALLBACK.onPermissionGranted();
                finish();
            } else {
                String[] permiss = new String[permissions.size()];
                permissions.toArray(permiss);
                if (Build.VERSION.SDK_INT >= 23) {
                    confirmRequestPermissions(permiss);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void confirmRequestPermissions(String[] permiss){
        String message = "我们需要一些必要的权限，来保证App的正常使用，请确认并授权：\n\n";
        boolean isGotoSetting = false;
        StringBuilder sb = new StringBuilder();
        Set<String> values = SPUtils.getInstance().getStringSet("reject_permissions");
        for (String permission : permiss) {
            //shouldShowRequestPermissionRationale false 第一次或者被永久拒绝
            if (!shouldShowRequestPermissionRationale(permission) && (values != null && values.contains(permission))) {
                isGotoSetting = true;
                message = "您拒绝了一些必要权限，App可能无法正常使用，请到系统权限管理中授予以下权限：\n\n";
            }
            sb.append(GPermission.permissionTips.get(permission)).append("\n");
        }
        boolean finalIsGotoSetting = isGotoSetting;
        customDialog = new CustomDialog(this)
                .setMessage(message + sb.toString())
                .setCancelEnable(false)
                .setMessageGravity(Gravity.START)
                .setMessagePadding(30, 30, 30, 10)
                .setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .setConfirmListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (finalIsGotoSetting){
                            GPermission.startSettings(Utils.getApp(), true);
                            finish();
                        } else {
                            requestPermissions(permiss, RC_REQUEST_PERMISSION);
                        }
                    }
                });
        customDialog.show();
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != RC_REQUEST_PERMISSION) {
            return;
        }
        boolean[] shouldShowRequestPermissionRationale = new boolean[permissions.length];
        for (int i = 0; i < permissions.length; ++i) {
            shouldShowRequestPermissionRationale[i] = shouldShowRequestPermissionRationale(permissions[i]);
        }
        this.onRequestPermissionsResult(permissions, grantResults, shouldShowRequestPermissionRationale);
    }

    @TargetApi(23)
    void onRequestPermissionsResult(String[] permissions, int[] grantResults, boolean[] shouldShowRequestPermissionRationale) {
        try {
            int length = permissions.length;
            int granted = 0;
            ArrayList<String> rationalPermissions = new ArrayList<>();
            ArrayList<String> rejectPermissions = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (shouldShowRequestPermissionRationale[i]) {
                        //CALLBACK.shouldShowRational(this, permissions[i]);
                        rationalPermissions.add(permissions[i]);
                    } else {
                        //CALLBACK.onPermissionReject(this, permissions[i]);
                        rejectPermissions.add(permissions[i]);
                        Set<String> values = SPUtils.getInstance().getStringSet("reject_permissions");
                        if (values == null){
                            values = new HashSet<>();
                        }
                        values.add(permissions[i]);
                        SPUtils.getInstance().put("reject_permissions", values);
                    }
                } else {
                    granted++;
                    Set<String> values = SPUtils.getInstance().getStringSet("reject_permissions");
                    if (values != null && values.contains(permissions[i])){
                        values.remove(permissions[i]);
                        SPUtils.getInstance().put("reject_permissions", values);
                    }
                }
            }
            Set<String> values = SPUtils.getInstance().getStringSet("reject_permissions");
            if (granted == length) {
                if (values != null && values.size() > 0){
                    GPermission.getPermission().request(this, values.toArray(new String[values.size()]));
                } else {
                    CALLBACK.onPermissionGranted();
                }
            } else if (rationalPermissions.size() > 0){
                //CALLBACK.shouldShowRational(this, rationalPermissions);
                GPermission.getPermission().request(this, rationalPermissions.toArray(new String[rationalPermissions.size()]));
            } else if (rejectPermissions.size() > 0){
                //CALLBACK.onPermissionReject(this, rejectPermissions);
                GPermission.getPermission().request(this, rejectPermissions.toArray(new String[rejectPermissions.size()]));
            }
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (customDialog != null){
            customDialog.dismiss();
        }
    }
}
