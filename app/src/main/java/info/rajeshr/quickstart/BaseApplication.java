package info.rajeshr.quickstart;

import android.app.Application;
import android.support.compat.BuildConfig;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;

import info.rajeshr.quickstart.Component.ApplicationComponent;
import info.rajeshr.quickstart.Component.DaggerApplicationComponent;
import info.rajeshr.quickstart.Helpers.Core.RetrofitStatic;
import info.rajeshr.quickstart.Models.OkHttpAuthModel;
import info.rajeshr.quickstart.Modules.AppModule;
import info.rajeshr.quickstart.Modules.ContextModule;
import info.rajeshr.quickstart.Modules.RetrofitModule;
import info.rajeshr.quickstart.Modules.ServiceModule;
import io.paperdb.Paper;
import timber.log.Timber;

public class BaseApplication extends Application {

    public static final String DEBUG_URL = "http://google.com";
    public static final String LIVE_URL = "http://google.com";

    //Basic Authentication
    public static final String BASIC_USER_NAME = "admin";
    public static final String BASIC_PASSWORD = "1234";

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initComponent();
        initBigImageViewer();
        initPaper();
    }

    private void initPaper() {
        Paper.init(getApplicationContext());
    }

    private void initBigImageViewer() {
        BigImageViewer.initialize(GlideImageLoader.with(getApplicationContext()));
    }

    private void initComponent() {
        appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule())
                .contextModule(new ContextModule(getApplicationContext()))
                .retrofitModule(new RetrofitModule(
                        RetrofitStatic.getBaseUrl(),
                        /*Pass Null To Skip Basic Auth*/
                        new OkHttpAuthModel(BASIC_USER_NAME, BASIC_PASSWORD)
                ))
                .serviceModule(new ServiceModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return appComponent;
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // Timber.plant(new TimberTree());
            Timber.plant(new Timber.DebugTree());
        }
    }
}
