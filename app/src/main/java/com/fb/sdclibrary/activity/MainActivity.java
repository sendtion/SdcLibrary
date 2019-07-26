package com.fb.sdclibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fb.sdclibrary.R;
import com.fb.sdclibrary.utils.UpdateManager;

import butterknife.OnClick;
import io.github.isliqian.NiceEmail;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initView() {

        new UpdateManager(this).checkUpdate(true);
    }

    @OnClick({R.id.tv_water_ripple_view, R.id.tv_water_ripple_sign, R.id.tv_date_picker, R.id.tv_recycler_view})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_water_ripple_view:
                //intent.setClass(this, RippleViewActivity.class);
                sendEmail();
                break;
            case R.id.tv_water_ripple_sign:
                intent.setClass(this, RippleSignActivity.class);
                break;
            case R.id.tv_date_picker:
                intent.setClass(this, PickerActivity.class);
                break;
            case R.id.tv_recycler_view:
                intent.setClass(this, RecyclerViewActivity.class);
                break;
            default:
                break;
        }
        //startActivity(intent);
    }

    private void sendEmail(){
        try {
            NiceEmail.subject("这是一封测试TEXT邮件")//主题
                    .from("LqNice")//发件人昵称
                    .to("???@qq.com")//收件人
                    .text("信件内容")//内容
                    .send();//发送
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
