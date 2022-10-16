package com.csc2002s.assgnmnts.apps.alphabet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.csc2002s.assgnmnts.apps.alphabet.adapters.FAV_PAGE_INDEX
import com.csc2002s.assgnmnts.apps.alphabet.adapters.ALPHABET_LIST_PAGE_INDEX
import com.csc2002s.assgnmnts.apps.alphabet.adapters.AlphabetApplicationPagerAdapter
import com.csc2002s.assgnmnts.apps.alphabet.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = AlphabetApplicationPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            FAV_PAGE_INDEX -> R.drawable.fav_tab_selector
            ALPHABET_LIST_PAGE_INDEX -> R.drawable.alphabet_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            FAV_PAGE_INDEX -> getString(R.string.fav_title)
            ALPHABET_LIST_PAGE_INDEX -> getString(R.string.alphabet_list_title)
            else -> null
        }
    }
}
