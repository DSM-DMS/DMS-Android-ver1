package teamdms.dms_kotlin.ViewPagerAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by root1 on 2017. 11. 25..
 */
class MainViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            else ->
        }
    }

    override fun getCount(): Int {
        return 4
    }

}