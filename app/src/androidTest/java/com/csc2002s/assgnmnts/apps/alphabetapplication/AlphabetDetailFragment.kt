package com.csc2002s.assgnmnts.apps.alphabetapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.composethemeadapter.MdcTheme
import com.csc2002s.assgnmnts.apps.alphabetapplication.compose.alphabetdetail.AlphabetDetailsScreen
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.Alphabet
import com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels.AlphabetDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography.dagger

/**
 * A fragment representing a single Alphabet detail screen.
 */
@AndroidEntryPoint
class AlphabetDetailFragment : Fragment() {

    private val alphabetDetailViewModel: AlphabetDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            // Create a Compose MaterialTheme inheriting the existing colors, typography
            // and shapes of the current View system's theme
            MdcTheme {
                AlphabetDetailsScreen(
                    alphabetDetailViewModel,
                    onBackClick = {
                        findNavController().navigateUp()
                    },
                    onShareClick = {
                        createShareIntent()
                    }
                )
            }
        }
    }

    private fun navigateToCollection() {
        alphabetDetailViewModel.alphabet.value?.let { alphabet: Alphabet ->
            val direction =
                AlphabetDetailFragmentDirections.actionAlphabetDetailFragmentToCollectionFragment(alphabet.name)
            findNavController().navigate(direction)
        }
    }

    // Helper function for calling a share functionality.
    // Should be used when user presses a share button/menu item.
    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = alphabetDetailViewModel.alphabet.value.let { alphabet: Alphabet ->
            if (alphabet == null) {
                ""
            } else {
                getString(R.string.share_text_alphabet, alphabet.name)
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }
}
