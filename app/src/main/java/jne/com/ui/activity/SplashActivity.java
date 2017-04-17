package jne.com.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import jne.com.base.BasePermissionActivity;
import jne.com.post.MainActivity;
import jne.com.R;


public class SplashActivity extends BasePermissionActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestPermission(new String[]{
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                });
            }
        }, 2000);
    }

    @Override
    protected void permissionSuccess() {
        skipActivity();
    }

    @Override
    protected void permissionFail() {
        finish();
    }

    private void skipActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
