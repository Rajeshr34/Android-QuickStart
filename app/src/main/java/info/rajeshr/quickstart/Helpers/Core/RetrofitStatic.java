package info.rajeshr.quickstart.Helpers.Core;


import android.content.Context;

import com.google.gson.Gson;

import info.rajeshr.quickstart.BaseApplication;
import info.rajeshr.quickstart.BuildConfig;
import info.rajeshr.quickstart.Helpers.Retrofit.OkHttpClientWithPool;
import info.rajeshr.quickstart.Helpers.Retrofit.ProgressConverterFactory;
import info.rajeshr.quickstart.Models.OkHttpAuthModel;
import info.rajeshr.quickstart.Network.DownloadService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitStatic {

    public static Retrofit getRetrofit(Gson gson, OkHttpClientWithPool okHttpClientWithPool, String baseUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(new ProgressConverterFactory(okHttpClientWithPool.getPool()))
                .baseUrl(baseUrl)
                .client(okHttpClientWithPool.getClient())
                .build();
    }

    public static Retrofit getRetrofitClone(Context context, OkHttpAuthModel okHttpAuthModel) {
        return getRetrofit(GsonStatic.getGson(),
                OkHttpStatic.getOkHttpClientWithPool(context, OkHttpStatic.getOkHttpCache(context), okHttpAuthModel), getBaseUrl());
    }

    public static DownloadService getDownloadService(Context context) {
        return getDownloadService(context, null);
    }

    public static DownloadService getDownloadService(Context context, OkHttpAuthModel okHttpAuthModel) {
        return getRetrofitClone(context, okHttpAuthModel).create(DownloadService.class);
    }

    public static String getBaseUrl() {
        return BuildConfig.DEBUG ? BaseApplication.DEBUG_URL : BaseApplication.LIVE_URL;
    }
}
