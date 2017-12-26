package teamdms.dms_kotlin.ViewPagerAdapter

import android.support.v4.app.*
import teamdms.dms_kotlin.Fragment.*

/**
 * Created by root1 on 2017. 11. 25..
 */
class MainViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){

    lateinit var myPageFragment: MyPageFragment
    lateinit var applyMainFragment: ApplyMainFragment

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MealFragment()
            1 -> {
                applyMainFragment = ApplyMainFragment()
                applyMainFragment
            }
            2 -> NoticeMainFragment()
            else -> {
                myPageFragment = MyPageFragment()
                myPageFragment
            }
        }
    }

    fun reload(position: Int = -1){
        when(position){
            -1 -> {
                applyMainFragment.load()
                myPageFragment.load()
            }
            1 -> applyMainFragment.load()
            3 -> myPageFragment.load()
        }
    }

    override fun getCount(): Int = 4

}