package com.frogobox.appsdk.piracy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.frogobox.appsdk.R;
import com.frogobox.appsdk.core.BaseActivity;
import com.frogobox.appsdk.databinding.ActivityPiracyBinding;
import com.frogobox.sdkutil.piracychecker.PiracyChecker;
import com.frogobox.sdkutil.piracychecker.enums.Display;
import com.frogobox.sdkutil.piracychecker.enums.InstallerID;
import com.frogobox.sdkutil.piracychecker.utils.LibraryUtilsKt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class PiracyMainActivity extends BaseActivity<ActivityPiracyBinding> {

    @NonNull
    @Override
    public ActivityPiracyBinding setupViewBinding() {
        return ActivityPiracyBinding.inflate(getLayoutInflater());
    }

    private Display piracyCheckerDisplay = Display.DIALOG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        setupUI();
    }

    private void setupUI() {

        getBinding().layoutContentMain.radioDisplay.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radio_dialog -> piracyCheckerDisplay = Display.DIALOG;
                case R.id.radio_activity -> piracyCheckerDisplay = Display.ACTIVITY;
                default -> piracyCheckerDisplay = Display.DIALOG;
            }
        });

        // Show APK signature
        for (String signature : LibraryUtilsKt.getApkSignatures(PiracyMainActivity.this)) {
            Log.e("Signature", signature);
        }

        getBinding().layoutContentMain.cvReadSignature.setOnClickListener(view -> readSignature());
        getBinding().layoutContentMain.cvVerifySignature.setOnClickListener(view -> verifySignature());
        getBinding().layoutContentMain.cvVerifyInstallerId.setOnClickListener(view -> verifyInstallerId());
        getBinding().layoutContentMain.cvVerifyUnauthorizedApps.setOnClickListener(view -> verifyUnauthorizedApps());
        getBinding().layoutContentMain.cvVerifyStores.setOnClickListener(view -> verifyStores());
        getBinding().layoutContentMain.cvVerifyDebug.setOnClickListener(view -> verifyDebug());
        getBinding().layoutContentMain.cvVerifyEmulator.setOnClickListener(view -> verifyEmulator());
        getBinding().fabToGithub.setOnClickListener(view -> toGithub());

    }

    public void toGithub() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/javiersantos/PiracyChecker")));
    }

    public void verifySignature() {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            //.enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
            .start();
    }

    public void readSignature() {
        StringBuilder dialogMessage = new StringBuilder();
        for (String signature : LibraryUtilsKt.getApkSignatures(PiracyMainActivity.this)) {
            Log.e("Signature", signature);
            dialogMessage.append("* ").append(signature).append("\n");
        }
        new AlertDialog.Builder(PiracyMainActivity.this)
            .setTitle("APK Signatures:")
            .setMessage(dialogMessage.toString())
            .show();
    }

    public void verifyInstallerId() {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableInstallerId(InstallerID.GOOGLE_PLAY)
            .start();
    }

    public void verifyUnauthorizedApps() {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableUnauthorizedAppsCheck()
            //.blockIfUnauthorizedAppUninstalled("license_checker", "block")
            .start();
    }

    public void verifyStores() {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableStoresCheck()
            .start();
    }

    public void verifyDebug() {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableDebugCheck()
            .start();
    }

    public void verifyEmulator() {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableEmulatorCheck(false)
            .start();
    }

}