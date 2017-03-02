package info.rajeshr.quickstart.Helpers.Core;


import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import info.rajeshr.quickstart.Helpers.FileHelper;
import info.rajeshr.quickstart.Helpers.Retrofit.OkHttpBaseInterceptor;
import info.rajeshr.quickstart.Helpers.Retrofit.OkHttpClientWithPool;
import info.rajeshr.quickstart.Helpers.Retrofit.ProgressInterceptor;
import info.rajeshr.quickstart.Helpers.Retrofit.ProgressListenerPool;
import info.rajeshr.quickstart.Models.OkHttpAuthModel;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

public class OkHttpStatic {

    private static final int DISK_CACHE_SIZE = 10; //10 MB
    private static final int OK_HTTP_TIME_OUT = 30; //in Seconds
    private static final String SHARED_TOKEN_KEY = "SHARED_TOKEN_KEY"; //in Seconds


    public static OkHttpClientWithPool getOkHttpClientWithPool(Context context, Cache cache, OkHttpAuthModel authModel) {
        return getOkHttpClientWithPool(context, cache, authModel, OK_HTTP_TIME_OUT);
    }

    public static OkHttpClientWithPool getOkHttpClientWithPool(Context context, Cache cache, final OkHttpAuthModel authModel, int timeOut) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.cache(cache);

        if (authModel != null) {
            clientBuilder.authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic(authModel.getUserName(), authModel.getPassword());
                    return response.request().newBuilder().header("Authorization", credential).build();
                }
            });
        }

        clientBuilder.readTimeout(timeOut, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(timeOut, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS);

        ProgressListenerPool pool = new ProgressListenerPool();

        clientBuilder.addInterceptor(new ProgressInterceptor(pool));
        clientBuilder.addInterceptor(new OkHttpBaseInterceptor(context));
        clientBuilder.addInterceptor(getHttpLoggingInterceptor());

        return new OkHttpClientWithPool(clientBuilder.build(), pool);
    }

    public static Cache getOkHttpCache(Context context) {
        return getOkHttpCache(context, DISK_CACHE_SIZE);
    }

    public static Cache getOkHttpCache(Context context, int cacheSize) {
        return new Cache(getCacheDirectory(context), (1024 * 1024 * cacheSize));
    }

    private static File getCacheDirectory(Context context) {
        File file = new File(context.getCacheDir(), "okhttp");
        FileHelper.createDir(file);
        return file;
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("OkHttp").d(message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }

    public static String getToken(Context context) {
        return getSharedToken(context).getString(SHARED_TOKEN_KEY, "");
    }

    public static void saveToken(Context context, String data) {
        SharedPreferences sharedPreferences = getSharedToken(context);
        String token = "";
        try {
            JSONObject json = new JSONObject(data);
            token = json.getString("customer_token");
        } catch (JSONException e) {
            Timber.tag("saveToken").e(e.getMessage());
        }
        Timber.d(token);
        sharedPreferences.edit().putString(SHARED_TOKEN_KEY, token).apply();
    }

    private static SharedPreferences getSharedToken(Context context) {
        return context.getSharedPreferences(SHARED_TOKEN_KEY, Context.MODE_PRIVATE);
    }
}
