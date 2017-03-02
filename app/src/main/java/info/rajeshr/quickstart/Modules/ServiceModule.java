package info.rajeshr.quickstart.Modules;

import dagger.Module;
import dagger.Provides;
import info.rajeshr.quickstart.Modules.Scopes.BaeApplicationScope;
import info.rajeshr.quickstart.Network.DownloadService;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class ServiceModule {

    @Provides
    @BaeApplicationScope
    public DownloadService getSyncService(Retrofit getRetrofit) {
        return getRetrofit.create(DownloadService.class);
    }

}
