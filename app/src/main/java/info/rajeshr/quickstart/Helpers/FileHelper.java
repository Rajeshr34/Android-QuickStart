package info.rajeshr.quickstart.Helpers;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import timber.log.Timber;

public class FileHelper {

    public static Boolean createDir(File dir) {
        return !dir.exists() && dir.mkdirs();
    }

    public static Boolean createFile(File file) {
        if (file.exists())
            return true;
        createDir(file.getParentFile());
        try {
            return file.createNewFile();
        } catch (IOException e) {
            printError("createFile", file, e);
        }
        return false;
    }

    public static String readFile(File file) {
        String data = "";
        try {
            data = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch (IOException e) {
            printError("readFile", file, e);
        }
        return data;
    }

    public static void writeFile(File file, String data, boolean append) {
        try {
            FileUtils.writeStringToFile(file, data, Charset.defaultCharset(), append);
        } catch (IOException e) {
            printError("writeFile", file, e);
        }
    }

    public static void writeFile(File file, byte[] data, boolean append) {
        try {
            FileUtils.writeByteArrayToFile(file, data, append);
        } catch (IOException e) {
            printError("writeFile", file, e);
        }
    }

    public static void deleteFile(File file) {
        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            printError("deleteFile", file, e);
        }
    }

    private static void printError(String tag, File file, IOException e) {
        Timber.tag(tag).e(String.format(Locale.ENGLISH, "%s | %s", file.getAbsoluteFile(), e.getMessage()));
    }
}
