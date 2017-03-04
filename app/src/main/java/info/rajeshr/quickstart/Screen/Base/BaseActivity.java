package info.rajeshr.quickstart.Screen.Base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import info.rajeshr.quickstart.BaseApplication;
import info.rajeshr.quickstart.Models.RetrofitNoInternetModel;
import info.rajeshr.quickstart.Network.DownloadService;


public class BaseActivity extends AppCompatActivity {

    @Inject
    public DownloadService getDownloadService;

    protected boolean toolbarBack() {
        return true;
    }

    protected boolean toolbarHide() {
        return false;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        loadToolbarMatrix();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectModule();
    }

    private void loadToolbarMatrix() {
        ActionBar tool = getSupportActionBar();
        if (tool != null) {
            if (toolbarBack()) {
                tool.setDisplayHomeAsUpEnabled(true);
            }
            if (toolbarHide()) {
                tool.hide();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void injectModule() {
        ((BaseApplication) getApplication()).getComponent().inject(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRetrofitNoInternet(RetrofitNoInternetModel retrofitNoInternetModel) {
        Toast.makeText(this, retrofitNoInternetModel.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
