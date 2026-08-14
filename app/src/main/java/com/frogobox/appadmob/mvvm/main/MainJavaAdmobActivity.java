package com.frogobox.appadmob.mvvm.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.frogobox.BaseActivity;
import com.frogobox.R;
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback;
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback;
import com.frogobox.databinding.ActivityMainAdmobBinding;
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize;
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardItem;

public class MainJavaAdmobActivity extends BaseActivity<ActivityMainAdmobBinding> implements FrogoAdmobInterstitialCallback, FrogoAdmobRewardedCallback {

    @NonNull
    @Override
    public ActivityMainAdmobBinding setupViewBinding() {
        return ActivityMainAdmobBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showAdBannerContainer(getString(R.string.admob_banner), AdSize.BANNER, getBinding().includeAdsView.frogoAdsBanner, null, null,null);
        showAdBanner(getBinding().adsXml.adsPhoneTabSpecialSmartBanner, null, null,null);
        hideButton();
        setupButtonClick();
        setupDetailActivity("Java Sample", null, null);
    }

    private void hideButton() {
        getBinding().btnJavaSampleActivity.setVisibility(View.GONE);
    }

    private void setupButtonClick() {
        getBinding().btnInterstitial.setOnClickListener(view -> {
            showAdInterstitial(getString(R.string.admob_interstitial),  null, null, this);
        });

        getBinding().btnRewarded.setOnClickListener(view -> {
            showAdRewarded(getString(R.string.admob_rewarded), null, null, this);
        });
    }

    @Override
    public void onShowAdRequestProgress(@NonNull String tag, @NonNull String message) {

    }

    @Override
    public void onHideAdRequestProgress(@NonNull String tag, @NonNull String message) {

    }

    @Override
    public void onAdDismissed(@NonNull String tag, @NonNull String message) {

    }

    @Override
    public void onAdFailed(@NonNull String tag, @NonNull String errorMessage) {

    }

    @Override
    public void onAdLoaded(@NonNull String tag, @NonNull String message) {

    }

    @Override
    public void onAdShowed(@NonNull String tag, @NonNull String message) {

    }

    @Override
    public void onUserEarnedReward(@NonNull String tag, @NonNull RewardItem rewardItem) {

    }
}