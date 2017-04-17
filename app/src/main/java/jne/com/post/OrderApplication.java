package jne.com.post;

import android.app.Application;

/**
 * 整个程序定制的Application对象。
 */
public class OrderApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        if (!OrderContext.isInitialized()) {
            OrderContext.init(getApplicationContext());
        }
    }
}
