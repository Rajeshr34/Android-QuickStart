package info.rajeshr.quickstart.Modules;

import android.content.Context;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import info.rajeshr.quickstart.Helpers.Core.OkHttpStatic;
import info.rajeshr.quickstart.Helpers.Core.RetrofitStatic;
import info.rajeshr.quickstart.Helpers.Retrofit.OkHttpClientWithPool;
import info.rajeshr.quickstart.Models.OkHttpAuthModel;
import info.rajeshr.quickstart.Modules.Scopes.BaeApplicationScope;
import okhttp3.Cache;
import retrofit2.Retrofit;

@Module(includes = {ContextModule.class, AppModule.class})
public class RetrofitModule {

    private String baseUrl;
    private OkHttpAuthModel auth;

    public RetrofitModule(String baseUrl, OkHttpAuthModel auth) {
        this.baseUrl = baseUrl;
        this.auth = auth;
    }

    @Provides
    @BaeApplicationScope
    Cache getOkHttpCache(Context context) {
        return OkHttpStatic.getOkHttpCache(context);
    }

    @Provides
    @BaeApplicationScope
    OkHttpClientWithPool getOkHttpClient(Cache cache, Context context) {
        return OkHttpStatic.getOkHttpClientWithPool(context, cache, auth);
    }

    @Provides
    @BaeApplicationScope
    Retrofit getRetrofit(Gson gson, OkHttpClientWithPool okHttpClientWithPool) {
        return RetrofitStatic.getRetrofit(gson, okHttpClientWithPool, baseUrl);
    }
}
