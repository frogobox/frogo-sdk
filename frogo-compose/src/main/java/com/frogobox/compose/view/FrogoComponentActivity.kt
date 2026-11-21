package com.frogobox.compose.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * Pure Jetpack Compose Base Activity extending [ComponentActivity].
 *
 * Designed for 100% Jetpack Compose applications:
 * - Eliminates XML Theme.AppCompat and Theme.MaterialComponents dependencies.
 * - Automatically configures modern Android Edge-to-Edge display via [enableEdgeToEdge].
 * - Provides lifecycle hooks, modern Activity Result APIs, and system UI controllers.
 *
 * For hybrid apps combining XML Views with Compose, use [FrogoComposeActivity].
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
abstract class FrogoComponentActivity : ComponentActivity() {

    companion object {
        val TAG: String = FrogoComponentActivity::class.java.simpleName
    }

    protected val frogoActivity: FrogoComponentActivity by lazy { this }

    // ---------------------------------------------------------------------------------------------
    // Activity Result API
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
        setupDoOnBackPressedExt()
        setupDelegates()
        setupViewModel()
        onCreateExt(savedInstanceState)
        setContent {
            SetupCompose()
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Edge To Edge
    // ---------------------------------------------------------------------------------------------
    open fun setupEnableEdgeToEdge() {
        enableEdgeToEdge()
    }

    // ---------------------------------------------------------------------------------------------
    // Back Press Handling
    // ---------------------------------------------------------------------------------------------

    /**
     * Determines whether [setupDoOnBackPressedExt] intercepts back press and invokes [finish].
     * Defaults to false to allow Compose Navigation (NavHost) and BackHandler to manage the back stack.
     */
    open var isBackPressFinishEnabled: Boolean = false

    /** Called when back button is pressed — default behavior is [finish] */
    open fun doOnBackPressedExt() {
        finish()
    }

    /** Manually triggers back press */
    open fun onBackPressedExt() {
        onBackPressedDispatcher.onBackPressed()
    }

    /** Setup modern back press listener with lifecycle handling */
    open fun setupDoOnBackPressedExt() {
        if (isBackPressFinishEnabled) {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() = doOnBackPressedExt()
                }
            )
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Setup Hooks
    // ---------------------------------------------------------------------------------------------
    open fun setupDebugMode(): Boolean = true
    open fun setupDelegates() {}
    open fun setupViewModel() {}
    open fun setupActivityResultExt(result: ActivityResult) {}
    open fun onCreateExt(savedInstanceState: Bundle?) {}

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
        WindowCompat.getInsetsController(window, window.decorView).apply {
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.systemBars())
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

    // ---------------------------------------------------------------------------------------------
    // Compose Entry Point
    // ---------------------------------------------------------------------------------------------

    /**
     * Override this in your Activity to provide the root Composable content.
     */
    @Composable
    abstract fun SetupCompose()
}
