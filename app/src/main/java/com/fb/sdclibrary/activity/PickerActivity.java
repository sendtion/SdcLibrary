package com.fb.sdclibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fb.sdclibrary.R;
import com.fb.sdclibrary.utils.DatePickerUtils;
import com.fb.sdclibrary.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;

public class PickerActivity extends BaseActivity {

    @BindView(R.id.tv_track_list_month)
    TextView mPickerMonth;
    @BindView(R.id.tv_track_list_day)
    TextView mPickerDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String currentDate = TimeUtils.date2String(new Date(), new SimpleDateFormat("yyyy-MM", Locale.getDefault()));
        mPickerMonth.setText(currentDate);
        currentDate = TimeUtils.date2String(new Date(), new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()));
        mPickerDay.setText(currentDate);
    }

    @OnClick({R.id.tv_track_list_month, R.id.tv_track_list_day})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_track_list_month:
                DatePickerUtils.showYearMonthPicker(this, new DatePicker.OnYearMonthPickListener(){

                    @Override
                    public void onDatePicked(String year, String month) {
                        String selectDate = year + "-" + month;
                        mPickerMonth.setText(selectDate);
                        showToast(selectDate);
                    }
                });
                break;
            case R.id.tv_track_list_day:
                DatePickerUtils.showYearMonthDayPicker(this, new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String selectDate = year + "-" + month + "-" + day;
                        mPickerDay.setText(selectDate);
                        showToast(selectDate);
                    }
                });
                break;
        }
    }
}
