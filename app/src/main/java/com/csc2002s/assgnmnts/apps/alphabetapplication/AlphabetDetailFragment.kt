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
                    },
                    onFabPrevClick = {
                        navigateToPrev()
                    },
                    onFabNextClick = {
                        navigateToNext()
                    },
                    onFabHomeClick = {
                        navigateToHome()
                    },
                    onFabToZClick = {
                        navigateToLastAlphabet()
                    },
                    onFabToAClick = {
                        navigateToFirstAlphabet()
                    }
                )
            }
        }
    }

    private fun navigateToCollection() {
        alphabetDetailViewModel.alphabet.value?.let { alphabet ->
            val direction =
                AlphabetDetailFragmentDirections.actionAlphabetDetailFragmentToCollectionFragment(alphabet.alphabetName)
            findNavController().navigate(direction)
        }
    }

    private fun navigateToNext() {
        alphabetDetailViewModel.alphabet.value?.let { alphabet ->
            var next: String = ""
            if (alphabet.alphabetName.equals("Aa")) {
                next = "Bb"
            }
            else if (alphabet.alphabetName.equals("Bb")) {
                next = "Cc"
            }
            else if (alphabet.alphabetName.equals("Cc")) {
                next = "Dd"
            }
            else if (alphabet.alphabetName.equals("Dd")) {
                next = "Ee"
            }
            else if (alphabet.alphabetName.equals("Ee")) {
                next = "Ff"
            }
            else if (alphabet.alphabetName.equals("Ff")) {
                next = "Gg"
            }
            else if (alphabet.alphabetName.equals("Gg")) {
                next = "Hh"
            }
            else if (alphabet.alphabetName.equals("Hh")) {
                next = "Ii"
            }
            else if (alphabet.alphabetName.equals("Ii")) {
                next = "Jj"
            }
            else if (alphabet.alphabetName.equals("Jj")) {
                next = "Kk"
            }
            else if (alphabet.alphabetName.equals("Kk")) {
                next = "Ll"
            }
            else if (alphabet.alphabetName.equals("Ll")) {
                next = "Mm"
            }
            else if (alphabet.alphabetName.equals("Mm")) {
                next = "Nn"
            }
            else if (alphabet.alphabetName.equals("Nn")) {
                next = "Oo"
            }
            else if (alphabet.alphabetName.equals("Oo")) {
                next = "Pp"
            }
            else if (alphabet.alphabetName.equals("Pp")) {
                next = "Qq"
            }
            else if (alphabet.alphabetName.equals("Qq")) {
                next = "Rr"
            }
            else if (alphabet.alphabetName.equals("Rr")) {
                next = "Ss"
            }
            else if (alphabet.alphabetName.equals("Ss")) {
                next = "Tt"
            }
            else if (alphabet.alphabetName.equals("Tt")) {
                next = "Uu"
            }
            else if (alphabet.alphabetName.equals("Uu")) {
                next = "Vv"
            }
            else if (alphabet.alphabetName.equals("Vv")) {
                next = "Ww"
            }
            else if (alphabet.alphabetName.equals("Ww")) {
                next = "Xx"
            }
            else if (alphabet.alphabetName.equals("Xx")) {
                next = "Yy"
            }
            else if (alphabet.alphabetName.equals("Yy")) {
                next = "Zz"
            }
            else{
                next = "Aa"
            }

            val direction =
                AlphabetDetailFragmentDirections.actionAlphabetDetailFragmentSelf(next)
            findNavController().navigate(direction)
        }
    }

    private fun navigateToPrev() {
        alphabetDetailViewModel.alphabet.value?.let { alphabet ->
            var prev: String = ""
            if (alphabet.alphabetName.equals("Aa")) {
                prev = "Zz"
            }
            else if (alphabet.alphabetName.equals("Bb")) {
                prev = "Aa"
            }
            else if (alphabet.alphabetName.equals("Cc")) {
                prev = "Bb"
            }
            else if (alphabet.alphabetName.equals("Dd")) {
                prev = "Cc"
            }
            else if (alphabet.alphabetName.equals("Ee")) {
                prev = "Dd"
            }
            else if (alphabet.alphabetName.equals("Ff")) {
                prev = "Ee"
            }
            else if (alphabet.alphabetName.equals("Gg")) {
                prev = "Ff"
            }
            else if (alphabet.alphabetName.equals("Hh")) {
                prev = "Gg"
            }
            else if (alphabet.alphabetName.equals("Ii")) {
                prev = "Hh"
            }
            else if (alphabet.alphabetName.equals("Jj")) {
                prev = "Ii"
            }
            else if (alphabet.alphabetName.equals("Kk")) {
                prev = "Jj"
            }
            else if (alphabet.alphabetName.equals("Ll")) {
                prev = "Kk"
            }
            else if (alphabet.alphabetName.equals("Mm")) {
                prev = "Ll"
            }
            else if (alphabet.alphabetName.equals("Nn")) {
                prev = "Mm"
            }
            else if (alphabet.alphabetName.equals("Oo")) {
                prev = "Nn"
            }
            else if (alphabet.alphabetName.equals("Pp")) {
                prev = "Oo"
            }
            else if (alphabet.alphabetName.equals("Qq")) {
                prev = "Pp"
            }
            else if (alphabet.alphabetName.equals("Rr")) {
                prev = "Qq"
            }
            else if (alphabet.alphabetName.equals("Ss")) {
                prev = "Rr"
            }
            else if (alphabet.alphabetName.equals("Tt")) {
                prev = "Ss"
            }
            else if (alphabet.alphabetName.equals("Uu")) {
                prev = "Tt"
            }
            else if (alphabet.alphabetName.equals("Vv")) {
                prev = "Uu"
            }
            else if (alphabet.alphabetName.equals("Ww")) {
                prev = "Vv"
            }
            else if (alphabet.alphabetName.equals("Xx")) {
                prev = "Ww"
            }
            else if (alphabet.alphabetName.equals("Yy")) {
                prev = "Xx"
            }
            else{
                prev = "Yy"
            }
            val direction =
                AlphabetDetailFragmentDirections.actionAlphabetDetailFragmentSelf(prev)
            findNavController().navigate(direction)
        }
    }

    private fun navigateToFirstAlphabet() {
        alphabetDetailViewModel.alphabet.value?.let { alphabet ->
            val direction =
                AlphabetDetailFragmentDirections.actionAlphabetDetailFragmentSelf("Aa")
            findNavController().navigate(direction)
        }
    }

    private fun navigateToLastAlphabet() {
        alphabetDetailViewModel.alphabet.value?.let { alphabet ->
            val direction =
                AlphabetDetailFragmentDirections.actionAlphabetDetailFragmentSelf("Zz")
            findNavController().navigate(direction)
        }
    }

    private fun navigateToHome() {
        alphabetDetailViewModel.alphabet.value?.let { alphabet ->
            val direction =
                AlphabetDetailFragmentDirections.actionAlphabetDetailFragmentToViewPagerFragment()
            findNavController().navigate(direction)
        }
    }

    // Helper function for calling a share functionality.
    // Should be used when user presses a share button/menu item.
    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = alphabetDetailViewModel.alphabet.value.let { alphabet ->
            if (alphabet == null) {
                ""
            } else {
                getString(R.string.share_text_alphabet, alphabet.alphabetName)
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
