package com.csc2002s.assgnmnts.apps.alphabetapplication.adapters

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.composethemeadapter.MdcTheme
import com.csc2002s.assgnmnts.apps.alphabetapplication.HomeViewPagerFragmentDirections
import com.csc2002s.assgnmnts.apps.alphabetapplication.AlphabetListFragment
import com.csc2002s.assgnmnts.apps.alphabetapplication.compose.alphabetlist.AlphabetListItemView
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.Alphabet

/**
 * Adapter for the [RecyclerView] in [AlphabetListFragment].
 */
class AlphabetAdapter : ListAdapter<Alphabet, RecyclerView.ViewHolder>(AlphabetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlphabetViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val alphabet = getItem(position)
        (holder as AlphabetViewHolder).bind(alphabet)
    }

    class AlphabetViewHolder(
        composeView: ComposeView
    ) : RecyclerView.ViewHolder(composeView) {
        fun bind(alphabet: Alphabet) {
            (itemView as ComposeView).setContent {
                MdcTheme {
                    AlphabetListItemView(alphabet = alphabet) {
                        navigateToAlphabet(alphabet)
                    }
                }
            }
        }

        private fun navigateToAlphabet(alphabet: Alphabet) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToAlphabetDetailFragment(
                    alphabet.alphabetId
                )
            itemView.findNavController().navigate(direction)
        }
    }
}

private class AlphabetDiffCallback : DiffUtil.ItemCallback<Alphabet>() {

    override fun areItemsTheSame(oldItem: Alphabet, newItem: Alphabet): Boolean {
        return oldItem.alphabetId == newItem.alphabetId
    }

    override fun areContentsTheSame(oldItem: Alphabet, newItem: Alphabet): Boolean {
        return oldItem == newItem
    }
}
