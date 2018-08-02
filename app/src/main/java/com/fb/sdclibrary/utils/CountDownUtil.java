package com.fb.sdclibrary.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 倒计时工具类
 */
public class CountDownUtil {
    private int time = 60;
    private Timer timer;
    private TextView send;
    private String endText;

    public CountDownUtil(TextView send, String endText) {
        super();
        this.send = send;
        this.endText = endText;
    }

    public void RunTimer() {
        time = 60;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                time--;
                Message msg = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(task, 100, 1000);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (time > 0) {
                        send.setEnabled(false);
                        send.setText(String.valueOf(time + "秒"));
                        send.setTextSize(14);
                    } else {
                        timer.cancel();
                        send.setText(endText);
                        send.setEnabled(true);
                        send.setTextSize(14);
                    }
                    break;
                default:
                    break;
            }
        }
    };


}