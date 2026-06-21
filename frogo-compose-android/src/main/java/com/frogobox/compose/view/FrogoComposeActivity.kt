package com.frogobox.compose.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import java.util.Calendar

abstract class FrogoComposeActivity : AppCompatActivity() {

    companion object {
        val TAG: String = FrogoComposeActivity::class.java.simpleName
    }

    protected val frogoActivity: FrogoComposeActivity by lazy { this }

    protected val textCopyright: String by lazy {
        "${getString(com.frogobox.sdk.R.string.about_all_right_reserved)} " +
                "${getString(com.frogobox.sdk.R.string.about_copyright)} ${Calendar.getInstance().get(Calendar.YEAR)}"
    }

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
        setupSetOnApplyWindowInsetsListener()
        setupDoOnBackPressedExt()
        setupDelegates()
        setupPiracyMode()
        setupMonetized()
        setupViewModel()
        onCreateExt(savedInstanceState)
        setContent {
            SetupCompose()
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Back Press Handling
    // ---------------------------------------------------------------------------------------------

    /** Called when back button pressed — default behavior is [finish] */
    open fun doOnBackPressedExt() {
        finish()
    }

    /** Allows manual trigger of back press */
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
    // Enable Edge To Edge
    // ---------------------------------------------------------------------------------------------
    open fun setupEnableEdgeToEdge() {
        enableEdgeToEdge()
    }

    // ---------------------------------------------------------------------------------------------
    // Setup Hooks
    // ---------------------------------------------------------------------------------------------
    open fun setupDebugMode(): Boolean = true
    open fun setupSetOnApplyWindowInsetsListener() {}
    open fun setupPiracyMode() {}
    open fun setupDelegates() {}
    open fun setupViewModel() {}
    open fun setupMonetized() {}
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

    // ---------------------------------------------------------------------------------------------
    // Compose Entry Point
    // ---------------------------------------------------------------------------------------------

    /**
     * Override this in your Activity to provide the root Composable content.
     * This is called inside [setContent] on [onCreate].
     */
    @Composable
    abstract fun SetupCompose()

    /**
     * Override this to provide a lightweight preview-friendly version of [SetupCompose].
     * Call this from a @Preview function in your concrete Activity class.
     *
     * Example usage in a subclass:
     * ```
     * @Preview
     * @Composable
     * fun PreviewMyActivity() {
     *     SetupComposePreview()
     * }
     *
     * @Composable
     * override fun SetupComposePreview() {
     *     MyActivityContent() // your composable without ViewModel/context deps
     * }
     * ```
     */
    @Composable
    open fun SetupComposePreview() {
        SetupCompose()
    }

}

// ---------------------------------------------------------------------------------------------
// Preview
// ---------------------------------------------------------------------------------------------

@Preview(name = "FrogoComposeActivity", showBackground = true, showSystemUi = true)
@Composable
private fun FrogoComposeActivityPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "FrogoComposeActivity",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Override SetupCompose() to build your screen",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        }
    }
}
