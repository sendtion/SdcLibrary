package com.fb.sdclibrary.permission;

public class PermissionCode {
    public static final String REQUEST_INSTALL_PACKAGES = "android.permission.REQUEST_INSTALL_PACKAGES"; // 8.0及以上应用安装权限

    public static final String SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW"; // 6.0及以上悬浮窗权限

    public static final String READ_CALENDAR = "android.permission.READ_CALENDAR"; // 读取日程提醒
    public static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR"; // 写入日程提醒

    public static final String CAMERA = "android.permission.CAMERA"; // 拍照权限

    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS"; // 读取联系人
    public static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS"; // 写入联系人
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS"; // 访问账户列表

    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION"; // 获取精确位置
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION"; // 获取粗略位置

    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO"; // 录音权限

    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE"; // 读取电话状态
    public static final String CALL_PHONE = "android.permission.CALL_PHONE"; // 拨打电话
    public static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG"; // 读取通话记录
    public static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG"; // 写入通话记录
    public static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL"; // 添加语音邮件
    public static final String USE_SIP = "android.permission.USE_SIP"; // 使用SIP视频
    public static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS"; // 处理拨出电话
    public static final String ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS";// 8.0危险权限：允许您的应用通过编程方式接听呼入电话。要在您的应用中处理呼入电话，您可以使用 acceptRingingCall() 函数
    public static final String READ_PHONE_NUMBERS = "android.permission.READ_PHONE_NUMBERS";// 8.0危险权限：权限允许您的应用读取设备中存储的电话号码

    public static final String BODY_SENSORS = "android.permission.BODY_SENSORS"; // 传感器

    public static final String SEND_SMS = "android.permission.SEND_SMS"; // 发送短信
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS"; // 接收短信
    public static final String READ_SMS = "android.permission.READ_SMS"; // 读取短信
    public static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH"; // 接收WAP PUSH信息
    public static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS"; // 接收彩信

    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"; // 读取外部存储
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE"; // 写入外部存储

    /********************************************************************/

    public static final String REQUEST_INSTALL_PACKAGES_STRING = "安装应用权限"; // 8.0及以上应用安装权限

    public static final String SYSTEM_ALERT_WINDOW_STRING = "悬浮窗权限"; // 6.0及以上悬浮窗权限

    public static final String READ_CALENDAR_STRING = "读取日程权限"; // 读取日程提醒
    public static final String WRITE_CALENDAR_STRING = "创建日程权限"; // 写入日程提醒

    public static final String CAMERA_STRING = "拍照权限"; // 拍照权限

    public static final String READ_CONTACTS_STRING = "联系人权限"; // 读取联系人
    public static final String WRITE_CONTACTS_STRING = "联系人权限"; // 写入联系人
    public static final String GET_ACCOUNTS_STRING = "系统账户权限"; // 访问账户列表

    public static final String ACCESS_FINE_LOCATION_STRING = "获取位置权限"; // 获取精确位置
    public static final String ACCESS_COARSE_LOCATION_STRING = "获取位置权限"; // 获取粗略位置

    public static final String RECORD_AUDIO_STRING = "录音权限"; // 录音权限

    public static final String READ_PHONE_STATE_STRING = "手机识别码权限"; // 读取电话状态
    public static final String CALL_PHONE_STRING = "拨打电话权限"; // 拨打电话
    public static final String READ_CALL_LOG_STRING = "读取通话记录权限"; // 读取通话记录
    public static final String WRITE_CALL_LOG_STRING = "写入通话记录权限"; // 写入通话记录
    public static final String ADD_VOICEMAIL_STRING = "添加语音邮件权限"; // 添加语音邮件
    public static final String USE_SIP_STRING = "使用SIP视频权限"; // 使用SIP视频
    public static final String PROCESS_OUTGOING_CALLS_STRING = "拨出电话权限"; // 处理拨出电话
    public static final String ANSWER_PHONE_CALLS_STRING = "响应拨打电话权限";// 8.0危险权限：允许您的应用通过编程方式接听呼入电话。要在您的应用中处理呼入电话，您可以使用 acceptRingingCall() 函数
    public static final String READ_PHONE_NUMBERS_STRING = "读取手机号权限";// 8.0危险权限：权限允许您的应用读取设备中存储的电话号码

    public static final String BODY_SENSORS_STRING = "使用传感器权限"; // 传感器

    public static final String SEND_SMS_STRING = "发送短信权限"; // 发送短信
    public static final String RECEIVE_SMS_STRING = "接收短信权限"; // 接收短信
    public static final String READ_SMS_STRING = "读取短信权限"; // 读取短信
    public static final String RECEIVE_WAP_PUSH_STRING = "接收推送权限"; // 接收WAP PUSH信息
    public static final String RECEIVE_MMS_STRING = "接收彩信权限"; // 接收彩信

    public static final String READ_EXTERNAL_STORAGE_STRING = "访问存储卡权限"; // 读取外部存储
    public static final String WRITE_EXTERNAL_STORAGE_STRING = "访问存储卡权限"; // 写入外部存储

    /********************************************************************/

    public static final String CAMERA_TIP = "相机权限用于扫描二维码或拍摄照片；"; // 拍照权限

    public static final String ACCESS_FINE_LOCATION_TIP = "位置权限用来添加设备时连接WiFi进行配网；"; // 获取精确位置
    public static final String ACCESS_COARSE_LOCATION_TIP = "位置权限用来添加设备时连接WiFi进行配网；"; // 获取粗略位置

    public static final String READ_PHONE_STATE_TIP = "手机识别码权限用来收集崩溃日志改善用户体验；"; // 读取电话状态
    public static final String CALL_PHONE_TIP = "拨打电话权限用来通过拨打电话联系我们；"; // 拨打电话

    public static final String READ_EXTERNAL_STORAGE_TIP = "读取存储卡权限用来从存储卡读取缓存图片以及读取崩溃日志数据；"; // 读取外部存储
    public static final String WRITE_EXTERNAL_STORAGE_TIP = "写入存储卡权限用来向存储卡缓存图片以及保存崩溃日志数据；"; // 写入外部存储

    public static final class Group {

        // 日历
        public static final String[] CALENDAR = new String[]{
                PermissionCode.READ_CALENDAR,
                PermissionCode.WRITE_CALENDAR};

        // 联系人
        public static final String[] CONTACTS = new String[]{
                PermissionCode.READ_CONTACTS,
                PermissionCode.WRITE_CONTACTS,
                PermissionCode.GET_ACCOUNTS};

        // 位置
        public static final String[] LOCATION = new String[]{
                PermissionCode.ACCESS_FINE_LOCATION,
                PermissionCode.ACCESS_COARSE_LOCATION};

        // 存储
        public static final String[] STORAGE = new String[]{
                PermissionCode.READ_EXTERNAL_STORAGE,
                PermissionCode.WRITE_EXTERNAL_STORAGE};
    }

}
