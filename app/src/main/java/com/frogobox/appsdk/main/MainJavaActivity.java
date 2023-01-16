package com.frogobox.appsdk.main;

import androidx.annotation.NonNull;

import com.frogobox.appsdk.core.BaseActivity;
import com.frogobox.appsdk.databinding.ActivityMainJavaBinding;

public class MainJavaActivity extends BaseActivity<ActivityMainJavaBinding> {

    private boolean isEmpty = false;

    @NonNull
    @Override
    public ActivityMainJavaBinding setupViewBinding() {
        return ActivityMainJavaBinding.inflate(getLayoutInflater());
    }

    @Override
    public void setupViewModel() {
    }

}