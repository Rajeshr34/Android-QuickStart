package info.rajeshr.quickstart.Helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class HashHelper {

    private static final String MD5 = "MD5";
    private static final String SHA = "SHA";

    @Nullable
    private static String hash(@NonNull final String algorithm, @NonNull final String string) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(string.getBytes());
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.setLength(0);
            final byte digest[] = messageDigest.digest();
            for (final byte b : digest) {
                stringBuilder.append(Integer.toString((b & 0xFF) + 0x100, StringHelper.HEX.length()).substring(1));
            }
            return stringBuilder.toString();
        } catch (final NoSuchAlgorithmException e) {

            return null;
        }
    }

    public static String md5(@NonNull final String string) {
        return hash(MD5, string);
    }

    public static String sha(@NonNull final String string) {
        return hash(SHA, string);
    }

    public static String randomMd5() {
        return md5(UUID.randomUUID().toString() + UUID.randomUUID().toString());
    }
}
