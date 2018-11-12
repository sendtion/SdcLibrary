package com.fb.sdclibrary.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;

import com.fb.sdclibrary.widget.CustomToast;

import java.io.File;
import java.util.Arrays;

public class SafetyUtils {
    /** install sucess */
    public static final int SUCCESS = 0;
    /** SIGNATURES_INVALIDATE */
    public static final int SIGNATURES_INVALIDATE = 3;
    /** SIGNATURES_NOT_SAME */
    public static final int VERIFY_SIGNATURES_FAIL = 4;
    /** is needcheck */
    public static final boolean NEED_VERIFY_CERT = true;
    /**
     * checkPagakgeSigns.
     */
    public static int checkPackageSign(Context context, String srcPluginFile) {
        PackageInfo PackageInfo = context.getPackageManager().getPackageArchiveInfo(srcPluginFile, PackageManager.GET_SIGNATURES);
        Signature[] pluginSignatures = PackageInfo.signatures;
//        Signature[] pluginSignatures = PackageVerifyer.collectCertificates(srcPluginFile, false);
        boolean isDebugable = (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
        if (pluginSignatures == null) {
            Log.e("签名验证失败", srcPluginFile);
            new File(srcPluginFile).delete();
            return SIGNATURES_INVALIDATE;
        } else if (NEED_VERIFY_CERT && !isDebugable) {
            //可选步骤，验证APK证书是否和现在程序证书相同。
            Signature[] mainSignatures = null;
            try {
                PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(
                        context.getPackageName(), PackageManager.GET_SIGNATURES);
                mainSignatures = pkgInfo.signatures;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (!Arrays.equals(mainSignatures, pluginSignatures)) {
                Log.e("升级包证书和旧版本证书不一致", srcPluginFile);
                new File(srcPluginFile).delete();
                return VERIFY_SIGNATURES_FAIL;
            }
        }
        return SUCCESS;
    }
    /**
     * checkPagakgeName
     * @param context
     * @param srcNewFile
     * @return
     */
    public static boolean checkPackageName (Context context, String srcNewFile) {
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(srcNewFile, PackageManager.GET_ACTIVITIES);
        if (packageInfo != null) {
            return TextUtils.equals(context.getPackageName(), packageInfo.packageName);
        }
        return false;
    }
    /**
     * checkFile
     *
     * @param aPath
     *            文件路径
     */
    public static boolean checkFile(String aPath) {
        File aFile = new File(aPath);
        if (aFile == null || !aFile.exists()) {
            CustomToast.getInstance(Utils.getApp()).showToastBottomShort("安装包已被恶意软件删除");
            return false;
        }
        return true;
    }
}