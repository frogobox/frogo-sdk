package com.frogobox.appsdk.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    @Override
    public void onCreateExt(@Nullable Bundle savedInstanceState) {
        super.onCreateExt(savedInstanceState);

    }

}