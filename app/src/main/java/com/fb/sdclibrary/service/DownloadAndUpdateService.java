package com.fb.sdclibrary.service;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAndUpdateService extends IntentService {
    private static final String TAG_DOWNLOAD = String.valueOf("DownloadAndUpdateService");

    public DownloadAndUpdateService() {
        // 实现父类的构造方法，用于命名工作线程，只用于调试。
        super("DownloadAndUpdateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        if (intent != null) {
            // 插件下载地址
            String urlPath = intent.getStringExtra("urlPath");
            // 插件下载后的存放路径
            String downloadDir = intent.getStringExtra("downloadDir");
            File file;
            try {
                URL url = new URL(urlPath);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.connect();
                int fileLength = httpURLConnection.getContentLength();// 文件大小
                String filePathUrl = httpURLConnection.getURL().getFile(); // 文件名
                String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
                URLConnection con = url.openConnection();
                //下载的逻辑
                BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
                String path = downloadDir + File.separatorChar + fileFullName;
                file = new File(path);
                if (!file.getParentFile().exists()) {
                    boolean mkdirs = file.getParentFile().mkdirs();
                }
                OutputStream out = new FileOutputStream(file);
                int size;
                int len = 0;
                byte[] buf = new byte[1024];
                while ((size = bin.read(buf)) != -1) {
                    len += size;
                    out.write(buf, 0, size);
                    // 下载百分比
                    int progress = len * 100 / fileLength;
                    Intent download = new Intent("download");
                    download.putExtra("progress", progress);
                    sendBroadcast(download);
                    Log.i(TAG_DOWNLOAD, "下载了-------> " + progress);
                }
                bin.close();
                out.close();
                // 升级安装插件新版本
                Intent download = new Intent("download_end");
                download.putExtra("path", path);
                sendBroadcast(download);
                //RePlugin.install(path);
                Log.i(TAG_DOWNLOAD, "下载完成 : " + path);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Log.i(TAG_DOWNLOAD, "final: ");
            }
        }
    }
}
