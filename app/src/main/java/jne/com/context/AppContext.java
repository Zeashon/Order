package jne.com.context;

import android.app.Application;

import jne.com.controller.MainController;
import jne.com.module.ApplicationModule;
import jne.com.module.library.ContextProvider;
import jne.com.module.library.InjectorModule;
import jne.com.module.qualifiers.ShareDirectory;
import jne.com.network.GsonHelper;
import jne.com.util.Injector;
import jne.com.util.PreferenceUtil;
import jne.com.util.ToastUtil;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class AppContext extends Application implements Injector {

    private static AppContext mInstance;

    private ObjectGraph mObjectGraph;
    private RefWatcher mRefWatcher;

    @Inject
    MainController mMainController;

    @Inject
    @ShareDirectory
    File mShareLocation;

    public static AppContext getContext() {
        return mInstance;
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public MainController getMainController() {
        return mMainController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // 吐司初始化
        ToastUtil.init(this);

        // 本地存储工具类初始化
        PreferenceUtil.init(this, GsonHelper.builderGson());

        // 日志打印器初始化
        Logger.init(getPackageName()).setLogLevel(AppConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);

        // bugly初始化
        CrashReport.initCrashReport(this, "1a859a4b54", false);

        // LeakCanary
        mRefWatcher = LeakCanary.install(this);

        // 依赖注解初始化
        mObjectGraph = ObjectGraph.create(
                new ApplicationModule(),
                new ContextProvider(this),
                new InjectorModule(this)
        );
        mObjectGraph.inject(this);
    }

    @Override
    public void inject(Object object) {
        mObjectGraph.inject(object);
    }
}