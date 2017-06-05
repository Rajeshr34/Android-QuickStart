package info.rajeshr.quickstart.Helpers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import timber.log.Timber;

public class RetrofitHelper {

    public static Bitmap getBitmap(Response<ResponseBody> response) {
        return getBitmap(response.body());
    }

    public static Bitmap getBitmap(ResponseBody body) {
        try {
            return BitmapFactory.decodeStream(body.byteStream());
        } catch (NullPointerException e) {
            Timber.e(e.getMessage());
        }
        return null;
    }

    public static String getString(ResponseBody body) {
        try {
            return body.string();
        } catch (Exception e) {
            Timber.e(e.getMessage());
        }
        return null;
    }

    public static String getString(Response<ResponseBody> responseBody) {
        return getString(responseBody.body());
    }

    public static byte[] getBytes(ResponseBody body) {
        try {
            return body.bytes();
        } catch (IOException e) {
            Timber.e(e.getMessage());
        }
        return null;
    }

    public static byte[] getBytes(Response<ResponseBody> responseBody) {
        return getBytes(responseBody.body());
    }

    public static boolean isSuccessful(Response<?> response) {
        if (response.isSuccessful()) {
            return true;
        } else {
            Timber.tag("Retrofit Error Message").e(response.message());
            Timber.tag("Retrofit Error Code").e(String.valueOf(response.code()));
            return false;
        }
    }

}
