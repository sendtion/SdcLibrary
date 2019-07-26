package com.fb.sdclibrary.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.util.List;

import static android.content.Context.WIFI_SERVICE;

public class NetUtil {
    private static final String TAG = "NetUtil";

    //    /**
    //     * 获取手机运营商
    //     * @return
    //     */
    //    public static int getNetIsmi(){
    //        TelephonyManager telManager = (TelephonyManager) MyApplication.context().getSystemService(Context
    // .TELEPHONY_SERVICE);
    //        String imsi = telManager.getSubscriberId();
    //        if(imsi!=null){
    //            if(imsi.startsWith("46000") || imsi.startsWith("46002")) {
    //                return DataType.NETWORK_IMSI_MOBILE;
    //            }else if(imsi.startsWith("46001")){
    //                return DataType.NETWORK_IMSI_UNICOM;
    //            }else if(imsi.startsWith("46003")){
    //                return DataType.NETWORK_IMSI_TELECOM;
    //            }
    //        }
    //        return DataType.NETWORK_IMSI_UNICOM;
    //    }

    /**
     * 判断wifi状态
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context
                    .CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null && mWiFiNetworkInfo.isConnected()) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断网络连接状态
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context
                    .CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断移动网络
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context
                    .CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null && mMobileNetworkInfo.isConnected()) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取连接类型
     *
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context
                    .CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }


    /**
     * 判断网络是否链接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            // 如果仅仅是用来判断网络连接
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            // return cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断GPS是否打开
     *
     * @param context
     * @return
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = lm.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }

    /**
     * 判断WIFI是否打开
     *
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo
                .State.CONNECTED) || mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断是否是3G网络
     *
     * @param context
     * @return
     */
    public static boolean is3rd(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 判断是wifi还是3g网络,用户的体现性在这里了，wifi就可以建议下载或者在线播放。
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 获取手机ip
     *
     * @param context
     * @return
     */
    //    public static String getIp(Context context) {
    //        String ipStr = "";
    //        if (com.allin.social.comm.utils.http.NetUtil.isWifiEnabled(context) || com.allin.social.comm.utils.http
    // .NetUtil.isGpsEnabled(context)) {
    //            if (com.allin.social.comm.utils.http.NetUtil.isNetworkAvailable(context)) {
    //                if (com.allin.social.comm.utils.http.NetUtil.isWifi(context)) {
    //                    ipStr = com.allin.social.comm.utils.http.NetUtil.getIpForWifi(context);
    //                } else {
    //                    ipStr = com.allin.social.comm.utils.http.NetUtil.getLocalIpAddress();
    //                }
    //            }
    //        }
    //        return ipStr;
    //    }

    /**
     * 获取手机外网ip
     *
     * @param context
     * @return
     */
    //    public static String getNetIp(Context context) {
    //        try {
    //            OkHttpUtil.get("http://ip.6655.com/ip.aspx", new ResultCallback<String>() {
    //                @Override
    //                public void onError(Request request, Exception e, int id) {
    //                    LogUtil.e(TAG, "-------ip URL+获取外网ip错误.request="+request + "    exception"+ e.getMessage());
    //                }
    //
    //                @Override
    //                public void onSuccessResponse(String responseString) {
    //                    LogUtil.i(TAG, "获取外网ip==" + responseString);
    //                    LogUtil.i(TAG, "获取外网ip成功");
    ///*                  Pattern pattern = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}
    // (?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
    //                    Matcher matcher = pattern.matcher(responseString);
    //                    if (matcher.find()) {
    //                    }*/
    //                    Const.NET_IP = responseString;
    //                    LibApp.setmOpIp(responseString);
    //                }
    //            });
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return "";
    //    }

    /**
     * wifi状态下获取手机ip ,需要以下权限 <uses-permission
     * android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
     * <uses-permission
     * android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
     * <uses-permission
     * android:name="android.permission.WAKE_LOCK"></uses-permission>
     *
     * @param context
     * @return
     */
    public static String getIpForWifi(Context context) {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    public static String getWiFISSID(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
        if (wm != null) {
            WifiInfo winfo = wm.getConnectionInfo();
            if (winfo != null) {
                String s = winfo.getSSID();
                if (s.length() > 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
                    return s.substring(1, s.length() - 1);
                }
            }
        }
        return "";
    }

    public static String getWiFIBSSID(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
        if (wm != null) {
            WifiInfo winfo = wm.getConnectionInfo();
            if (winfo != null) {
                String s = winfo.getBSSID();
                return s == null ? "" : s;
            }
        }
        return "";
    }

    public static boolean isWiFi5G(Context context){
        int freq = 0;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            freq = wifiInfo.getFrequency();
        } else {
            String ssid = wifiInfo.getSSID();
            if (ssid != null && ssid.length() > 2) {
                String ssidTemp = ssid.substring(1, ssid.length() - 1);
                List<ScanResult> scanResults = wifiManager.getScanResults();
                for (ScanResult scanResult : scanResults) {
                    if (scanResult.SSID.equals(ssidTemp)) {
                        freq = scanResult.frequency;
                        break;
                    }
                }
            }
        }
        return freq > 4900 && freq < 5900;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    /**
     * 将URL中的特殊字符进行转译
     *
     * @param url
     * @return
     */
    public static String urlReplace(String url) {
        if (url.contains("{"))
            url = url.replaceAll("\\{", "%7b");
        if (url.contains("}"))
            url = url.replaceAll("\\}", "%7d");
        if (url.contains("\""))
            url = url.replaceAll("\"", "%22");
        return url;
    }
}
