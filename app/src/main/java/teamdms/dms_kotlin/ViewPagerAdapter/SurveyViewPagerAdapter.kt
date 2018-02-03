package teamdms.dms_kotlin.ViewPagerAdapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import teamdms.dms_kotlin.Base.BaseFragment
import teamdms.dms_kotlin.Model.Notice

/**
 * Created by 윤정현 on 2018-01-03.
 */
class SurveyViewPagerAdapter(fm :FragmentManager,fragment: List<BaseFragment> ) : FragmentStatePagerAdapter(fm){

    private var fragments : List<Fragment>? = null

    init {
        this.fragments=fragment
    }

    override fun getItem(position: Int): Fragment {
        return fragments!![position]
    }

    override fun getCount(): Int {
        return fragments!!.size
    }


}