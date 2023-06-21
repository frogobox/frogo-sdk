package com.frogobox.appsdk.piracy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    }

    public void toGithub(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/javiersantos/PiracyChecker")));
    }

    public void verifySignature(View view) {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            //.enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
            .start();
    }

    public void readSignature(View view) {
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

    public void verifyInstallerId(View view) {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableInstallerId(InstallerID.GOOGLE_PLAY)
            .start();
    }

    public void verifyUnauthorizedApps(View view) {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableUnauthorizedAppsCheck()
            //.blockIfUnauthorizedAppUninstalled("license_checker", "block")
            .start();
    }

    public void verifyStores(View view) {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableStoresCheck()
            .start();
    }

    public void verifyDebug(View view) {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableDebugCheck()
            .start();
    }

    public void verifyEmulator(View view) {
        new PiracyChecker(PiracyMainActivity.this)
            .display(piracyCheckerDisplay)
            .enableEmulatorCheck(false)
            .start();
    }

}