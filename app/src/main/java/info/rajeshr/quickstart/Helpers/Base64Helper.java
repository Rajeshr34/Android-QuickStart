package info.rajeshr.quickstart.Helpers;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.UnsupportedEncodingException;

import timber.log.Timber;


public class Base64Helper {

    private static final int FLAGS = Base64.NO_WRAP;

    protected Base64Helper() {
        // Empty
    }

    public static String encrypt(@NonNull final String string) {
        return Base64.encodeToString(string.getBytes(), FLAGS);
    }

    public static String encrypt(@NonNull final byte[] bytes) {
        return Base64.encodeToString(bytes, FLAGS);
    }

    public static String decrypt(@NonNull final String string) {
        return new String(Base64.decode(string, FLAGS));
    }

    public static String decrypt(@NonNull final byte[] bytes) {
        return Base64.encodeToString(bytes, FLAGS);
    }

    public static String byteToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Timber.e(e.getMessage());
        }
        return "";
    }
}
