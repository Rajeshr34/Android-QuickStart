package info.rajeshr.quickstart.Network;


import info.rajeshr.quickstart.Helpers.Retrofit.ProgressListener;
import info.rajeshr.quickstart.Helpers.Retrofit.annotation.DownloadProgress;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface DownloadService {
    @GET
    Call<ResponseBody> get(@Url String fileUrl, @DownloadProgress @Header(DownloadProgress.HEADER) ProgressListener listener);
}
