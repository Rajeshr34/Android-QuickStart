package info.rajeshr.quickstart.Libraries;


import android.content.Context;

import com.google.gson.Gson;

import java.io.File;

import info.rajeshr.quickstart.Helpers.FileHelper;


public class PaperDB {

    private static Context context;

    public static void init(Context context) {
        PaperDB.context = context;
    }

    private static File getPaperDbFolder() {
        return new File(context.getCacheDir(), "paperDb");
    }

    public static File getFile(String key) {
        File fileCache = new File(getPaperDbFolder(), File.separator + "." + key);
        FileHelper.createFile(fileCache);
        return fileCache;
    }

    public static <T> T read(String key, Object defaultValue) {
        String readData = FileHelper.readFile(getFile(key));
        T value;
        try {
            value = (T) new Gson().fromJson(readData, defaultValue.getClass());
        } catch (Exception ignored) {
            value = (T) defaultValue;
        }
        if (value == null)
            value = (T) defaultValue;
        return value;
    }


    public static void write(String key, Object dataObject) {
        String data = new Gson().toJson(dataObject);
        FileHelper.writeFile(getFile(key), data, false);
    }

    public static void delete(String key) {
        FileHelper.deleteFile(getFile(key));
    }

    public static void destroy() {
        FileHelper.deleteFile(getPaperDbFolder());
    }
}
