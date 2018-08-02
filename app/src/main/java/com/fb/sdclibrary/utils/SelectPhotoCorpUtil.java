package com.fb.sdclibrary.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.fb.sdclibrary.constant.StaticConstants;

import java.io.File;


public class SelectPhotoCorpUtil {
    public static final int REQUEST_PICK_CONTACT = 20;//选择相册请求码
    public static final int REQUEST_IMAGE_CAPTURE = 21;//拍照请求码
    public static final int REQUEST_IMAGE_CROP = 22;//裁剪图片请求码
    public static final int SELECT_CHANNEL = 23;//选择图片渠道请求码
    public static final int SELECT_CHANNEL_CONTACT_REQUEST_CODE = 24;//选择图片渠道_相册结果码
    public static final int SELECT_CHANNEL_CAPTURE_REQUEST_CODE = 25;//选择图片渠道_相机结果码
    public static final String CACHE_NAME = "xfezt";
    private Context mContext;
    private Fragment mFragment;
    private String tempImagePath;//裁剪后图片所在的文件夹
    private String tempImageName = System.currentTimeMillis() + ".jpg";//裁剪后的文件名
    private String userHeaderImage;//裁剪后的文件路径
    public static final String CACHE_PATHE = Environment.getExternalStorageDirectory().getPath() + "/"
            + StaticConstants.APPLICATION_NAME + "/";

    public SelectPhotoCorpUtil(Context context, Fragment fragment) {
        mContext = context;
        mFragment = fragment;
        mkdir(getTempImagePath());
        setUserHeaderImage(tempImagePath + tempImageName);
    }

    /**
     * 裁剪图片
     */
    public void cropImageUri(Uri uri, int outputX, int outputY) {
        if (uri == null) {
            Toast.makeText(mContext, "出错", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(userHeaderImage)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        if (mFragment != null) {
            mFragment.startActivityForResult(intent, REQUEST_IMAGE_CROP);
        } else {
            ((Activity) mContext).startActivityForResult(intent, REQUEST_IMAGE_CROP);
        }

    }

    /**
     * 拍照
     */
    public void takePhoto() {
        Uri imageUri = Uri.fromFile(new File(userHeaderImage));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        if (mFragment != null) {
            mFragment.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            ((Activity) mContext).startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * 选择相册
     */
    public void selectAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (mFragment != null) {
            mFragment.startActivityForResult(intent, REQUEST_PICK_CONTACT);
        } else {
            ((Activity) mContext).startActivityForResult(intent, REQUEST_PICK_CONTACT);
        }
    }

    /**
     * 获取裁剪图片临时文件夹路径
     */
    private String getProfileImagePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(CACHE_PATHE);
        sb.append("image");
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 创建文件夹
     */
    private boolean mkdir(String path) {
        try {
            File dirFile = new File(path);
            boolean bFile = dirFile.exists();
            if (bFile == true) {
                return true;
            } else {
                bFile = dirFile.mkdirs();
                if (bFile == true) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    /**
     * 删除所有图片
     */
    public void deleteAllImage(String path) {
        File file = new File(path);
        if (file != null || file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }
        }
    }

    public String getTempImagePath() {
        if (tempImagePath == null) {
            tempImagePath = getProfileImagePath();
        }
        return tempImagePath;
    }

    public void setTempImageName(String tempImageName) {
        this.tempImageName = tempImageName;
        setUserHeaderImage(tempImagePath + tempImageName);
    }

    public String getUserHeaderImage() {
        return userHeaderImage;
    }

    private void setUserHeaderImage(String userHeaderImage) {
        this.userHeaderImage = userHeaderImage;
    }
}
