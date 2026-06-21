package com.frogobox.compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Marker interface for Composable Screen components within the Frogo SDK.
 *
 * Implement this interface in your screen-level Composable objects or classes
 * to enforce a consistent contract: each screen must provide both its main
 * content ([Content]) and a preview-friendly version ([Preview]).
 *
 * Example:
 * ```kotlin
 * object MyScreen : FrogoComposeScreen {
 *
 *     @Composable
 *     override fun Content() {
 *         MyScreenContent()
 *     }
 *
 *     @Preview(showBackground = true)
 *     @Composable
 *     override fun Preview() {
 *         MyScreenContent()
 *     }
 * }
 * ```
 */
interface FrogoComposeScreen {

    /**
     * The main Composable content of this screen.
     * Override to define the actual UI rendered at runtime.
     */
    @Composable
    fun Content()

    /**
     * A preview-friendly version of [Content].
     * Override and annotate with [@Preview][Preview] in your implementation
     * to enable Android Studio Compose Preview for this screen.
     */
    @Preview(showBackground = true)
    @Composable
    fun Preview()

}

// ---------------------------------------------------------------------------------------------
// Preview
// ---------------------------------------------------------------------------------------------

@Preview(name = "FrogoComposeScreen", showBackground = true, showSystemUi = true)
@Composable
private fun FrogoComposeScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    )
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "F",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "FrogoComposeScreen",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Implement Content() and Preview()\nto build your screen",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, start = 32.dp, end = 32.dp)
            )
        }
    }
}
