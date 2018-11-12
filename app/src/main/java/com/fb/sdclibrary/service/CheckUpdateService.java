package com.fb.sdclibrary.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.fb.sdclibrary.utils.SafetyUtils;
import com.fb.sdclibrary.utils.Utils;
import com.fb.sdclibrary.widget.CustomToast;

import java.io.File;

public class CheckUpdateService extends Service {
    private String TAG = "CheckUpdateService";
    private DownloadManager downloadManager;
    private long mTaskId;
    private String downloadPath;

    public CheckUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            String url = intent.getStringExtra("url");
            String appName = "xfezt" + System.currentTimeMillis() + ".apk";
            downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + appName;
            downloadAPK(url, appName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //使用系统下载器下载
    private void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);

        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir("/download/", versionName);
//        request.setDestinationInExternalFilesDir(Utils.getApp(),StaticConstants.CACHE_PATHE+"apk/",versionName);//也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        this.registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };

    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    Log.i(TAG, ">>>下载暂停");
                case DownloadManager.STATUS_PENDING:
                    Log.i(TAG, ">>>下载延迟");
                case DownloadManager.STATUS_RUNNING:
                    Log.i(TAG, ">>>正在下载");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    Log.i(TAG, ">>>下载完成");
                    //下载完成安装APK
                    //downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + versionName;
                    installAPK(downloadPath);
                    break;
                case DownloadManager.STATUS_FAILED:
                    Log.i(TAG, ">>>下载失败");
                    break;
            }
        }
    }

    //下载到本地后执行安装
    protected void installAPK(String downloadPath) {
        if (!SafetyUtils.checkFile(downloadPath)) {
            return;
        }
        if (!SafetyUtils.checkPackageName(getBaseContext(), downloadPath)) {
            CustomToast.getInstance(Utils.getApp()).showToastBottomShort("升级包被恶意软件篡改 请重新升级下载安装");
            return;
        }
        switch (SafetyUtils.checkPackageSign(Utils.getApp(), downloadPath)) {
            case SafetyUtils.SUCCESS:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("file://" + downloadPath);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                //在服务中开启activity必须设置flag,后面解释
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
                break;
            case SafetyUtils.SIGNATURES_INVALIDATE:
                CustomToast.getInstance(Utils.getApp()).showToastBottomShort("升级包安全校验失败 请重新升级");
                break;
            case SafetyUtils.VERIFY_SIGNATURES_FAIL:
                CustomToast.getInstance(Utils.getApp()).showToastBottomShort("升级包为盗版应用 请重新升级");
                break;
            default:
                break;
        }
    }
}
