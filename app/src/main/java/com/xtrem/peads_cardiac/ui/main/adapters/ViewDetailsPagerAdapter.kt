package com.xtrem.peads_cardiac.ui.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xtrem.peads_cardiac.ui.main.home.view.CardiacEcoFragment
import com.xtrem.peads_cardiac.ui.main.home.view.DetailsFragment
import com.xtrem.peads_cardiac.ui.main.home.view.FcbFragment
import com.xtrem.peads_cardiac.ui.main.home.view.UandEsDetailsFragment

class ViewDetailsPagerAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> return DetailsFragment()
            1 -> return FcbFragment()
            2 -> return CardiacEcoFragment()
            3 -> return UandEsDetailsFragment()

        }
        return DetailsFragment()
    }

    override fun getItemCount(): Int {
        return 4
    }
}
