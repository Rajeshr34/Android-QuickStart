package info.rajeshr.quickstart.Helpers.Retrofit;


import okhttp3.OkHttpClient;

public class OkHttpClientWithPool {
    private final OkHttpClient client;
    private final ProgressListenerPool pool;

    public OkHttpClientWithPool(OkHttpClient client, ProgressListenerPool pool) {
        this.client = client;
        this.pool = pool;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public ProgressListenerPool getPool() {
        return pool;
    }
}
