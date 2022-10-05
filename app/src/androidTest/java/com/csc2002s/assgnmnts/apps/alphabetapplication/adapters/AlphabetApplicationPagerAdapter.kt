package com.csc2002s.assgnmnts.apps.alphabetapplication.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.csc2002s.assgnmnts.apps.alphabetapplication.FavFragment
import com.csc2002s.assgnmnts.apps.alphabetapplication.AlphabetListFragment

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

class AlphabetApplicationPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        FAV_PAGE_INDEX to { FavFragment() },
        ALPHABET_LIST_PAGE_INDEX to { AlphabetListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
