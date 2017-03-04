package info.rajeshr.quickstart.Screen.Main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import info.rajeshr.quickstart.R;
import info.rajeshr.quickstart.Screen.Base.BaseActivity;
import info.rajeshr.quickstart.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity {

    @Override
    protected boolean toolbarBack() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setSelected("xxxxxxxxxxxxxx");

    }


}
