package com.csc2002s.assgnmnts.apps.alphabetapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.csc2002s.assgnmnts.apps.alphabetapplication.HomeViewPagerFragmentDirections
import com.csc2002s.assgnmnts.apps.alphabetapplication.R
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.AlphabetAndFavAdding
import com.csc2002s.assgnmnts.apps.alphabetapplication.databinding.ListItemFavAddingBinding
import com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels.AlphabetAndFavAddingViewModel

class FavAddingAdapter :
    ListAdapter<AlphabetAndFavAdding, FavAddingAdapter.ViewHolder>(
        FavAlphabetDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_fav_adding,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemFavAddingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.viewModel?.alphabetId?.let { alphabetId ->
                    navigateToAlphabet(alphabetId, view)
                }
            }
        }

        private fun navigateToAlphabet(alphabetId: String, view: View) {
            val direction = HomeViewPagerFragmentDirections
                .actionViewPagerFragmentToAlphabetDetailFragment(alphabetId)
            view.findNavController().navigate(direction)
        }

        fun bind(practiceAlphabets: AlphabetAndFavAdding) {
            with(binding) {
                viewModel = AlphabetAndFavAddingViewModel(practiceAlphabets)
                executePendingBindings()
            }
        }
    }
}

private class FavAlphabetDiffCallback : DiffUtil.ItemCallback<AlphabetAndFavAdding>() {

    override fun areItemsTheSame(
        oldItem: AlphabetAndFavAdding,
        newItem: AlphabetAndFavAdding
    ): Boolean {
        return oldItem.alphabet.alphabetId == newItem.alphabet.alphabetId
    }

    override fun areContentsTheSame(
        oldItem: AlphabetAndFavAdding,
        newItem: AlphabetAndFavAdding
    ): Boolean {
        return oldItem.alphabet == newItem.alphabet
    }
}
