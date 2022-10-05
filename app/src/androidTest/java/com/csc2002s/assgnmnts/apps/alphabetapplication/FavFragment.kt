package com.csc2002s.assgnmnts.apps.alphabetapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.csc2002s.assgnmnts.apps.alphabetapplication.adapters.FavAddingAdapter
import com.csc2002s.assgnmnts.apps.alphabetapplication.adapters.ALPHABET_LIST_PAGE_INDEX
import com.csc2002s.assgnmnts.apps.alphabetapplication.databinding.FragmentFavBinding
import com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels.FavAddingListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding

    private val viewModel: FavAddingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        val adapter = FavAddingAdapter()
        binding.favList.adapter = adapter

        binding.addAlphabet.setOnClickListener {
            navigateToAlphabetListPage()
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: FavAddingAdapter, binding: FragmentFavBinding) {
        viewModel.alphabetAndFavAdding.observe(viewLifecycleOwner) { result ->
            binding.hasFav = result.isNotEmpty()
            adapter.submitList(result) {
                // At this point, the content should be drawn
                activity?.reportFullyDrawn()
            }
        }
    }

    // TODO: convert to data binding if applicable
    private fun navigateToAlphabetListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            ALPHABET_LIST_PAGE_INDEX
    }
}
