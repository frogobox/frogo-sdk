package com.frogobox.sdk.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.frogobox.sdk.R
import com.frogobox.sdk.ext.getColorExt
import com.frogobox.sdk.ext.getDrawableExt
import java.util.Calendar

/**
 * Base Activity Class for FrogoSDK
 *
 * Provides lifecycle hooks, result handling, UI system controls,
 * and standard extension points for ViewModel, EdgeToEdge, and delegates.
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */

abstract class FrogoActivity : AppCompatActivity() {

    companion object {
        val TAG: String = FrogoActivity::class.java.simpleName
    }

    protected val frogoActivity: FrogoActivity by lazy { this }

    protected val textCopyright: String by lazy {
        "${getString(R.string.about_all_right_reserved)} " +
                "${getString(R.string.about_copyright)} ${Calendar.getInstance().get(Calendar.YEAR)}"
    }

    // ---------------------------------------------------------------------------------------------
    // Activity Result API (modern replacement for onActivityResult)
    // ---------------------------------------------------------------------------------------------
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            setupActivityResultExt(result)
        }

    // ---------------------------------------------------------------------------------------------
    // Lifecycle
    // ---------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupEnableEdgeToEdge()
        setupContentView()
        setupSetOnApplyWindowInsetsListener()
        setupDoOnBackPressedExt()
        setupDelegates()
        setupPiracyMode()
        setupMonetized()
        setupViewModel()
        onCreateExt(savedInstanceState)
    }

    // ---------------------------------------------------------------------------------------------
    // Back Press Handling
    // ---------------------------------------------------------------------------------------------

    /** Called when back button pressed — default behavior is [finish] */
    open fun doOnBackPressedExt() {
        finish()
    }

    /** Allows manual trigger of back press from child fragments or components */
    open fun onBackPressedExt() {
        onBackPressedDispatcher.onBackPressed()
    }

    /** Setup modern back press listener for all Android versions */
    open fun setupDoOnBackPressedExt() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                doOnBackPressedExt()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() = doOnBackPressedExt()
                }
            )
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Setup Hooks — override freely in subclasses
    // ---------------------------------------------------------------------------------------------
    open fun setupDebugMode(): Boolean = true
    open fun setupEnableEdgeToEdge() {}
    open fun setupSetOnApplyWindowInsetsListener() {}
    open fun setupPiracyMode() {}
    open fun setupDelegates() {}
    open fun setupViewModel() {}
    open fun setupMonetized() {}
    open fun setupContentView() {}
    open fun setupActivityResultExt(result: ActivityResult) {}
    open fun onCreateExt(savedInstanceState: Bundle?) {}

    // ---------------------------------------------------------------------------------------------
    // Toolbar / ActionBar Utility
    // ---------------------------------------------------------------------------------------------
    open fun setupDetailActivity(
        title: String,
        @DrawableRes actionBackIcon: Int? = null,
        @ColorRes backgroundColor: Int? = null
    ) {
        supportActionBar?.apply {
            this.title = title
            setDisplayHomeAsUpEnabled(true)

            actionBackIcon?.let { setHomeAsUpIndicator(getDrawableExt(it)) }
            backgroundColor?.let { setBackgroundDrawable(getColorExt(it).toDrawable()) }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Menu Handling
    // ---------------------------------------------------------------------------------------------
    override fun onCreateOptionsMenu(menu: Menu): Boolean = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                doOnBackPressedExt()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Fragment Handling
    // ---------------------------------------------------------------------------------------------
    open fun setupChildFragment(frameId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            if (addToBackStack) addToBackStack(fragment::class.java.simpleName)
            commit()
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Activity Result Helpers
    // ---------------------------------------------------------------------------------------------
    open fun startActivityResultExt(intent: Intent) {
        activityResultLauncher.launch(intent)
    }

    // ---------------------------------------------------------------------------------------------
    // System UI Controls
    // ---------------------------------------------------------------------------------------------
    /** Force fullscreen mode */
    open fun setupFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    /** Hide system bars (immersive mode) */
    open fun setupHideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
