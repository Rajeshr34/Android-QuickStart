package info.rajeshr.quickstart.Libraries;


import android.content.Context;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import info.rajeshr.quickstart.Helpers.FileHelper;


public class PaperDB {


    private static Context context;

    public static void init(Context context) {
        PaperDB.context = context;
    }

    private static File getPaperDbFolder() {
        return new File(context.getCacheDir(), "paperDb");
    }

    private static File getFile(String key) {
        return new File(getPaperDbFolder(), File.separator + "." + key);
    }

    private static File getBackFile(String key) {
        return new File(getPaperDbFolder(), File.separator + "." + key + ".bak");
    }

    public static <T> T read(String key, Object defaultValue) {
        File originalFile = getFile(key);
        File backUpFile = getBackFile(key);

        if (backUpFile.exists()) {
            deleteSingleFile(originalFile);
            backUpFile.renameTo(originalFile);
        }

        if (!exist(key)) {
            return (T) defaultValue;
        }

        String readData = FileHelper.readFile(originalFile);

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

    private static boolean exist(String key) {
        return getFile(key).exists();
    }


    public static void write(String key, Object dataObject) {
        String data = new Gson().toJson(dataObject);
        File originalFile = getFile(key);
        File backFile = getBackFile(key);

        if (originalFile.exists()) {
            if (!backFile.exists()) {
                //rename original
                if (!originalFile.renameTo(backFile)) {

                }
            } else {
                originalFile.delete();
            }
        }
        write_(originalFile, data, backFile);
    }

    private static void write_(File file, String data, File backFile) {
        try {
            FileUtils.writeStringToFile(file, data, Charset.defaultCharset(), false);
            deleteSingleFile(backFile);
        } catch (IOException e) {
            deleteSingleFile(file);
        }
    }

    private static void deleteSingleFile(File file) {
        if (file.exists())
            file.delete();
    }

    public static void delete(String key) {
        FileHelper.deleteFile(getFile(key));
        FileHelper.deleteFile(getBackFile(key));
    }

    public static void destroy() {
        FileHelper.deleteFile(getPaperDbFolder());
    }
}