package info.rajeshr.quickstart.Helpers.Retrofit.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DownloadProgress {

    String HEADER = "X-DProgress";
}
