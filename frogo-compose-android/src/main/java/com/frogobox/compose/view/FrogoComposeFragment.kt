package com.frogobox.compose.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

abstract class FrogoComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent {
                setupCompose()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    open fun setupViewModel() {}

    /**
     * Override this in your Fragment to provide the root Composable content.
     */
    @Composable
    abstract fun setupCompose()

    /**
     * Override this to provide a preview-friendly version of [setupCompose].
     * Call this from a @Preview function in your concrete Fragment class.
     *
     * Example usage in a subclass:
     * ```
     * @Preview
     * @Composable
     * fun PreviewMyFragment() {
     *     setupComposePreview()
     * }
     *
     * @Composable
     * override fun setupComposePreview() {
     *     MyFragmentContent() // your composable without Fragment/ViewModel deps
     * }
     * ```
     */
    @Composable
    open fun setupComposePreview() {
        setupCompose()
    }

}
