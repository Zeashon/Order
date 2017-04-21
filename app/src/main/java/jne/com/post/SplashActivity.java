package jne.com.post;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import jne.com.R;


public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                skipActivity();
            }
        }, 2000);
    }


    private void skipActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
