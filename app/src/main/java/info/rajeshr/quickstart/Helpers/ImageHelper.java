package info.rajeshr.quickstart.Helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageHelper {

    public static File compressImage(File file, Bitmap bitmap, int newWidth) {
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width > newWidth) {

            float scaleWidth = ((float) newWidth) / width;
            float ratio = (float) width / newWidth;
            int newHeight = (int) (height / ratio);
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            writeBitmapToFile(file, resizedBitmap);
        } else {
            writeBitmapToFile(file, bitmap);
        }

        return file;
    }

    public static File writeBitmapToFile(File file, Bitmap bitmap) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outStream);
        FileHelper.writeFile(file, outStream.toByteArray(), false);
        return file;
    }

    public static Bitmap createCircleBitmap(Bitmap bitMapping) {
        if (bitMapping == null)
            return null;
        Bitmap output = Bitmap.createBitmap(bitMapping.getWidth(), bitMapping.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitMapping.getWidth(), bitMapping.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitMapping.getWidth() / 2, bitMapping.getHeight() / 2, bitMapping.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitMapping, rect, rect, paint);
        return output;
    }
}
