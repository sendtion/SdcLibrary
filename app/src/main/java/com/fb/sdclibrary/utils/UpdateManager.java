package com.fb.sdclibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.fb.sdclibrary.service.CheckUpdateService;
import com.fb.sdclibrary.widget.UpdateDialog;

public class UpdateManager {

    private Context mContext;
    private boolean isForceUpdate = false;

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(final boolean isToast) {
        //showNoticeDialog("更新内容测试", "", "v 1.5.8");

//        String url = SPUtils.getInstance().getString(StaticApiConstants.LOGIN_CHECK_VERSION);
//        Map<String, Object> params = CommonParametersUtil.MethodGetSign();
//        params.put("version_code", BuildConfig.VERSION_CODE);
//        DataManagerUtil.getInstance().checkVersion(url, params, new DataManagerUtil.CallBackData<HttpResponse<DataVersionEntry, Object>>() {
//            @Override
//            public void onSuccess(HttpResponse<DataVersionEntry, Object> dataVersionEntryObjectHttpResponse) {
//                if (dataVersionEntryObjectHttpResponse != null) {
//                    DataVersionEntry data = dataVersionEntryObjectHttpResponse.getData();
//                    if (data != null) {
//                        DataVersionEntry.UpgradeBean upgrade = data.getUpgrade();
//                        if (upgrade != null) {
//                            long need_update = upgrade.getNeed_update();
//                            if (need_update == 1) {
//                                String dUrl = upgrade.getUrl();
//                                if (TextUtils.isEmpty(dUrl)) {
//                                    CustomToast.getmInstance(Utils.getApp()).showToastBottomShort(mContext.getResources().getString(R.string.software_address_error));
//                                    return;
//                                }
//                                String desc = upgrade.getDesc();
//                                long mode = upgrade.getMode();
//                                if (1 == mode) {
//                                    isForceUpdate = true;
//                                } else {
//                                    isForceUpdate = false;
//                                }
//                                showNoticeDialog(desc, dUrl, "v " + upgrade.getLatest_version());
//                            } else {
//                                if (isToast) {
//                                    Toast.makeText(mContext, R.string.current_is_latest, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//        });
    }


    /**
     * 显示更新对话框
     */

    private void showNoticeDialog(String version_info, final String url, String newVersion) {
        final UpdateDialog updateDialog = new UpdateDialog(mContext);
        updateDialog.setTitle(String.format("发现新版本%s", newVersion));
        updateDialog.setMessage(version_info);
        updateDialog.setForceUpdate(isForceUpdate);
        updateDialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
                Intent service = new Intent(mContext, CheckUpdateService.class);
                service.putExtra("url", url);
                mContext.startService(service);
            }
        });
        updateDialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
            }
        });
        updateDialog.show();
    }
}
