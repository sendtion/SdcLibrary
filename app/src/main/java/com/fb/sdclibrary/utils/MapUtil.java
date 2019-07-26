package com.fb.sdclibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.fb.sdclibrary.widget.CustomToast;

/**
 * 地图工具类
 */
public class MapUtil {
    private static final String BAIDU_PACKAGE = "com.baidu.BaiduMap";
    private static final String GAODE_PACKAGE = "com.autonavi.minimap";
    private static final String TENCENT_PACKAGE = "com.tencent.map";

    private static boolean isPackageInstalled(Context mContext, String packagename) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static void startBaidu(Context activity,double latitude,double longitude){
        // 百度地图
        if (isPackageInstalled(activity,BAIDU_PACKAGE)) {
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("baidumap://map/navi?location=" + latitude + "," + longitude));
            activity.startActivity(naviIntent);
        }else {
            CustomToast.getInstance(Utils.getApp()).showToastBottomShort("请先安装百度地图");
        }
    }

    public static void startGaode(Context activity,double latitude,double longitude){
        // 高德地图
        if (isPackageInstalled(activity,GAODE_PACKAGE)) {
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("androidamap://route?sourceApplication=appName&slat=&slon=&sname=我的位置&dlat="+ latitude +"&dlon="+longitude+"&dname=目的地&dev=0&t=2"));
            activity.startActivity(naviIntent);
        }else {
            CustomToast.getInstance(Utils.getApp()).showToastBottomShort("请先安装高德地图");
        }
    }

    public static void startTencent(Context activity,double latitude,double longitude){
        // 腾讯地图
        if (isPackageInstalled(activity,TENCENT_PACKAGE)) {
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=drive&from=&fromcoord=&to=目的地&tocoord=" + latitude + "," + longitude + "&policy=0&referer=appName"));
            activity.startActivity(naviIntent);
        }else {
            CustomToast.getInstance(Utils.getApp()).showToastBottomShort("请先安装腾讯地图");
        }

    }

}
