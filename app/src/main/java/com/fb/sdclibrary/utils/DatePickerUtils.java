package com.fb.sdclibrary.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.fb.sdclibrary.R;

import java.util.Date;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;

/**
 * Description:
 * CreateTime: 2018/8/24 17:14
 * Author: ShengDecheng
 */

public class DatePickerUtils {

    /**
     * 显示日期选择器
     */
    public static void showYearMonthPicker(Activity activity, DatePicker.OnYearMonthPickListener onDatePicker) {
        String currentDate = TimeUtils.date2String(new Date());
        int year = DateUtils.getYearFromString(currentDate);
        int month = DateUtils.getMonthFromString(currentDate);
        //int day = DateUtils.getDayFromString(currentDate);

        final DatePicker picker = new DatePicker(activity, DateTimePicker.YEAR_MONTH);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true); //是否等分
        picker.setContentPadding(50, 10);
        picker.setRangeStart(2017, 1); //设置开始年月日
        picker.setRangeEnd(year, month); //设置结束年月日
        picker.setSelectedItem(year, month); //设置默认选择日期
        picker.setResetWhileWheel(false);
        picker.setTopLineVisible(false); //设置顶部线是否显示
        //picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setDividerVisible(false); //设置选中分割线是否显示
        //picker.setDividerRatio(WheelView.DividerConfig.FILL);
        //picker.setCycleDisable(false); //是否循环显示
        picker.setAnimationStyle(R.style.AnimationCustomPopup); //设置显示动画
        picker.setCancelText("取消");
        picker.setCancelTextColor(ContextCompat.getColor(activity, R.color.color_a));
        picker.setCancelTextSize(activity.getResources().getDimensionPixelSize(R.dimen.text_size_4));
        picker.setSubmitText("完成");
        picker.setSubmitTextColor(ContextCompat.getColor(activity, R.color.color_38a2f0));
        picker.setSubmitTextSize(activity.getResources().getDimensionPixelSize(R.dimen.text_size_4));
        picker.setTextColor(ContextCompat.getColor(activity, R.color.color_3));
        picker.setTextSize(activity.getResources().getDimensionPixelSize(R.dimen.text_size_4));
        picker.setLabelTextColor(ContextCompat.getColor(activity, R.color.color_3));
        picker.setLabel("-", "", "");
        picker.setOnDatePickListener(onDatePicker);
        picker.show();
    }

    /**
     * 显示日期选择器
     */
    public static void showYearMonthDayPicker(Activity activity, DatePicker.OnYearMonthDayPickListener onDatePicker) {
        String currentDate = TimeUtils.date2String(new Date());
        int year = DateUtils.getYearFromString(currentDate);
        int month = DateUtils.getMonthFromString(currentDate);
        int day = DateUtils.getDayFromString(currentDate);

        final DatePicker picker = new DatePicker(activity);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true); //是否等分
        picker.setContentPadding(50, 10);
        picker.setRangeStart(2017, 1, 1); //设置开始年月日
        picker.setRangeEnd(year, month, day); //设置结束年月日
        picker.setSelectedItem(year, month, day); //设置默认选择日期
        picker.setResetWhileWheel(false);
        picker.setTopLineVisible(false); //设置顶部线是否显示
        //picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setDividerVisible(false); //设置选中分割线是否显示
        //picker.setDividerRatio(WheelView.DividerConfig.FILL);
        //picker.setCycleDisable(false); //是否循环显示
        picker.setAnimationStyle(R.style.AnimationCustomPopup); //设置显示动画
        picker.setCancelText("取消");
        picker.setCancelTextColor(ContextCompat.getColor(activity, R.color.color_a));
        picker.setCancelTextSize(activity.getResources().getDimensionPixelSize(R.dimen.text_size_4));
        picker.setSubmitText("完成");
        picker.setSubmitTextColor(ContextCompat.getColor(activity, R.color.color_38a2f0));
        picker.setSubmitTextSize(activity.getResources().getDimensionPixelSize(R.dimen.text_size_4));
        picker.setTextColor(ContextCompat.getColor(activity, R.color.color_3));
        picker.setTextSize(activity.getResources().getDimensionPixelSize(R.dimen.text_size_4));
        picker.setLabelTextColor(ContextCompat.getColor(activity, R.color.color_3));
        picker.setLabel("-", "-", "");
        picker.setOnDatePickListener(onDatePicker);
        picker.show();
    }
}
