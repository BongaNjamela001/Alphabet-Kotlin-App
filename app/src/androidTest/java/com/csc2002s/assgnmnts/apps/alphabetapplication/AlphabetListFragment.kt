package com.csc2002s.assgnmnts.apps.alphabetapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.csc2002s.assgnmnts.apps.alphabetapplication.adapters.AlphabetAdapter
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.Alphabet
import com.csc2002s.assgnmnts.apps.alphabetapplication.databinding.FragmentOverviewListBinding
import com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels.AlphabetListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlphabetListFragment : Fragment() {

    private val viewModel: AlphabetListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = AlphabetAdapter()
        binding.overviewList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_alphabet_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: AlphabetAdapter) {
        viewModel.alphabets.observe(viewLifecycleOwner) { alphabets ->
            adapter.submitList(alphabets)
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearPracticeZoneNumber()
            } else {
                setPracticeZoneNumber(9)
            }
        }
    }
}
