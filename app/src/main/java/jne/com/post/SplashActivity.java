package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import jne.com.R;


public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //1、打开Preferences，名称为addSetting，如果存在则打开它，否则创建新的Preferences
        SharedPreferences addrSetting = getSharedPreferences("addrSetting", Activity.MODE_PRIVATE);
        //2、取出数据
        String checked = addrSetting.getString("checked","N");
//        if addrSetting doesn't exist or user didn't give their addr
        if(checked.equals("N")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    skipActivityToAddr();
                }
            }, 2000);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    skipActivityToMain();
                }
            }, 2000);
        }


    }


    private void skipActivityToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    private void skipActivityToAddr() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
