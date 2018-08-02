package com.fb.sdclibrary.constant;

import android.os.Environment;

/**
 * desc  : 静态常量
 */
public final class StaticConstants {
    //private static final String BASE_URL = "http://172.16.10.93:8089/#";
    public static final String CACHE_PATHE = Environment.getExternalStorageDirectory().getPath() + "/"
            + StaticConstants.APPLICATION_NAME + "/";

    public static final String CHART_TYPE = "chart_type";
    public static final String USER_TYPE_ID = "user_type_id";
    public static final String ROLE = "role";
    public static final String ROLE_ID = "role_id";
    public static final String TOKEN = "token";
    public static final String FIRE_AUTHORITIES_ID = "fire_authorities_id";
    public static final String FIRE_AUTHORITIES_NAME = "fire_authorities_name";
    public static final String COMPANY_ID = "company_id";
    public static final String COMPANY_TYPE = "company_type";
    public static final String MENUS = "menus";
    public static final String USER_ID = "user_id";
    public static final String ACCOUNT = "account";
    public static final String COMPANY_NAME = "company_name";
    public static final String OWNER_ID = "owner_id";
    public static final String ACCOUNT_NUMBER = "account_number";
    public static final String ACCOUNT_PWD = "account_pwd";
    public static final String REMEMBER_ACCOUNT = "remember_account";
    public static final String LOOP_NUMBER = "loop_number";
    public static final String COMPONENT_NUMBER = "component_number";
    public static final String DTU_ID = "dtu_id";
    public static final String COMPONENT_TYPE = "component_type";
    public static final String CHANGE_TIME = "change_time";
    public static final String RECEIVE_TIME = "receive_time";
    public static final String DETAIL = "detail";
    public static final String HOST_TYPE = "host_type";
    public static final String ITEM = "item";
    public static final String PHONE = "phone";
    public static final String CODE = "code";
    public static final String AVATAR = "avatar";
    public static final String NAME = "name";
    public static final String MAP_STYLE_TYPE = "map_style_type";
    public static final String MAP_INFO_TYPE = "map_info_type";
    public static final String SCAN_RESULT = "scan_result";
    public static final String VERSION = "version";
    public static final String PATROL_POINT_ID = "patrol_point_id";
    public static final String LOCATION = "location";
    public static final String DANGER_ID = "danger_id";
    public static final String CURRENT_ITEM = "current_item";
    public static final String SELECT_COMPANY_SEARCH_HISTORY = "select_company_search_history";
    public static final String APPLICATION_NAME = "xfezt";
    public static final String LANGUAGE = "language";

    /*********跳转navigation***********/

    public static final String NAVIGATION_MESSAGE = "fb_notice";
    public static final String NAVIGATION_AROUND = "fb_around";
    public static final String NAVIGATION_APPLICATION = "fb_apps";
    public static final String NAVIGATION_CHECK = "fb_check";
    public static final String NAVIGATION_CHECK_COMPANY = "fb_check_company";
    public static final String NAVIGATION_MINE = "fb_mine";
    public static final String NAVIGATION_HYDRANT_LIST = "fb_hydrant_list";


    /** 周边信息类型标记 **/
//    public static final String MARKER_ICON_COMPANY = "marker_icon_company"; //联网单位
//    public static final String MARKER_ICON_HIDDEN = "marker_icon_hidden"; // 隐患
//    public static final String MARKER_ICON_CHEMICAL = "marker_icon_chemical"; //化学品
//    public static final String MARKER_ICON_CONDUIT = "marker_icon_conduit"; // 管道分布
//    public static final String MARKER_ICON_HYDRANT = "marker_icon_hydrant"; // 室外消火栓
//    public static final String MARKER_ICON_FLOOR = "marker_icon_floor"; // 建筑物

    public static final int MAX_CHOOSE_COUNT = 3; // 周边图片最大选择图片数量
    public static final int DEVICE_MODEL_ID_PRESSURE = 11016; // 压力监测终端ID
    public static final int DEVICE_MODEL_ID_STUFFY = 11017; // 智能闷盖ID
    public static final int DEVICE_MODEL_ID_UNIFY = 11018; // 一体化监测设备ID
    public static final String DEVICE_MODEL_NAME_PRESSURE = "消火栓压力监测终端";
    public static final String DEVICE_MODEL_NAME_STUFFY = "消火栓智能闷盖";
    public static final String DEVICE_MODEL_NAME_UNIFY = "多功能智能闷盖";
    public static final int DEVICE_TYPE_ID_STUFFY = 10008; //智能闷盖
    public static final int DEVICE_TYPE_ID_PRESSURE = 10003; //压力监测终端
    public static final int DEVICE_TYPE_ID_UNIFY = 10008; //一体化设备
    public static final int DEVICE_CLASS_ID_STUFFY = 10011; //独立式设备
    public static final int DEVICE_CLASS_ID_PRESSURE = 10011; //独立式设备
    public static final int DEVICE_CLASS_ID_UNIFY = 10011; //独立式设备

    public static final int DEVICE_STATUS_ONLINE = 666; //设备在线
    public static final int DEVICE_STATUS_OFFLINE = 555; //设备离线

    public static final int HYDRANT_PIPE_DIAMETER_65 = 65; //DN65
    public static final int HYDRANT_PIPE_DIAMETER_100 = 100; //DN100
    public static final int HYDRANT_PIPE_DIAMETER_150 = 150; //DN150
}
