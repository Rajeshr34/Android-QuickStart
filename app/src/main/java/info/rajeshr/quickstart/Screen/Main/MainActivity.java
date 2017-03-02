package info.rajeshr.quickstart.Screen.Main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import info.rajeshr.quickstart.Helpers.FileHelper;
import info.rajeshr.quickstart.Helpers.ImageHelper;
import info.rajeshr.quickstart.Helpers.Retrofit.ProgressListener;
import info.rajeshr.quickstart.Helpers.RetrofitHelper;
import info.rajeshr.quickstart.Helpers.ShareHelper;
import info.rajeshr.quickstart.R;
import info.rajeshr.quickstart.Screen.Base.BaseActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Override
    protected boolean toolbarBack() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView text = (ImageView) findViewById(R.id.text);
        final Button share = (Button) findViewById(R.id.share);

        final File file = new File(getApplicationContext().getFilesDir(), "xxx.png");
        FileHelper.createFile(file);

        getDownloadService.get("https://www.google.co.in/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png", new ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength) {
                // Timber.e(bytesRead + "");
                // Timber.e(contentLength + "");
            }
        }).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (RetrofitHelper.isSuccessful(response)) {
                    ImageHelper.writeBitmapToFile(file, RetrofitHelper.getBitmap(response));
                }
                Timber.d("Download Finished");
                // text.setImageBitmap(RetrofitHelper.getBitmap(response));
                //Glide.with(MainActivity.this).load(RetrofitHelper.getBitmap(response.body())).into(text);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Timber.d("Download Failed");
            }
        });

        //Glide.with(this).load("http://www.gstatic.com/webp/gallery/1.webp").into(text);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareHelper.shareImage(file.getAbsolutePath(), "sadas", "asda", MainActivity.this);
            }
        });

    }

}
