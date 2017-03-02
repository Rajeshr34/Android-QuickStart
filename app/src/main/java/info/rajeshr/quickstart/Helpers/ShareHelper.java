package info.rajeshr.quickstart.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShareHelper {

    public static ArrayList<Uri> filesToUri(List<File> files, Context context) {
        ArrayList<Uri> uri = new ArrayList<>();
        for (File item : files) {
            uri.add(filesToUri(item, context));
        }
        return uri;
    }

    public static Uri filesToUri(File item, Context context) {
        String packageName = context.getApplicationContext().getPackageName();
        String authority = packageName + ".fileprovider";
        return FileProvider.getUriForFile(context, authority, item);
    }

    public static void sharePdf(Uri file, String subject, Activity activity) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, file);
        activity.startActivity(Intent.createChooser(shareIntent, subject));
    }

    public static void sharePdf(String filePath, String Subject, Activity activity) {
        sharePdf(filesToUri(new File(filePath), activity), Subject, activity);
    }

    public static void shareImage(ArrayList<Uri> files, String Subject, String Description, Activity activity) {
        if (files.size() <= 0)
            return;
        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.setType("image/*");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Subject);
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Description);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
        activity.startActivity(Intent.createChooser(shareIntent, Subject));
    }

    public static void shareImage(final String filePath, String subject, String description, Activity activity) {
        shareImage(Collections.singletonList(filePath), subject, description, activity);
    }

    public static void shareImage(List<String> files, String subject, String description, Activity activity) {
        ArrayList<Uri> uris = new ArrayList<>();
        for (String file : files) {
            uris.add(filesToUri(new File(file), activity));
        }
        shareImage(uris, subject, description, activity);
    }

}
