package teamdms.dms_kotlin.ViewPagerAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import teamdms.dms_kotlin.Fragment.ApplyMainFragment
import teamdms.dms_kotlin.Fragment.IntroDeveloperFragment
import teamdms.dms_kotlin.Fragment.MealContentFragment

/**
 * Created by dsm2016 on 2017-12-28.
 */

class IntroViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return IntroDeveloperFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 5
    }

}
