package info.rajeshr.quickstart.Helpers.Retrofit;


import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import info.rajeshr.quickstart.Helpers.Core.OkHttpStatic;
import info.rajeshr.quickstart.Models.RetrofitNoInternetModel;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import timber.log.Timber;

public class OkHttpBaseInterceptor implements Interceptor {

    private Context context;

    public OkHttpBaseInterceptor(Context context) {
        this.context = context;
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request newRequest = request.newBuilder().header("token", OkHttpStatic.getToken()).build();

        Response response;
        try {
            response = chain.proceed(newRequest);
        } catch (Exception e) {
            EventBus.getDefault().postSticky(new RetrofitNoInternetModel(e.getMessage()));
            throw e;
        }

        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        if (isPlaintext(buffer)) {
            MediaType contentType = responseBody.contentType();
            String data = buffer.clone().readString(contentType.charset(Charset.forName("UTF-8")));
            OkHttpStatic.saveToken(context, data);
            Timber.d(data);
        } else {
            Timber.d("Response Type is binary");
        }
        return response;
    }
}
