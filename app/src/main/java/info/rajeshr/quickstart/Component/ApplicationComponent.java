package info.rajeshr.quickstart.Component;

import dagger.Component;
import info.rajeshr.quickstart.Modules.AppModule;
import info.rajeshr.quickstart.Modules.ContextModule;
import info.rajeshr.quickstart.Modules.RetrofitModule;
import info.rajeshr.quickstart.Modules.Scopes.BaeApplicationScope;
import info.rajeshr.quickstart.Modules.ServiceModule;
import info.rajeshr.quickstart.Network.DownloadService;
import info.rajeshr.quickstart.Screen.Base.BaseActivity;
import retrofit2.Retrofit;

@BaeApplicationScope
@Component(modules = {ContextModule.class, AppModule.class, RetrofitModule.class, ServiceModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Retrofit getRetrofit();

    DownloadService getDownloadService();

}
