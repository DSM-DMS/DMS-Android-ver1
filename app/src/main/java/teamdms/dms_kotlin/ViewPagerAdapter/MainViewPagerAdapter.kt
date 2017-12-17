package teamdms.dms_kotlin.ViewPagerAdapter

import android.support.v4.app.*
import teamdms.dms_kotlin.Fragment.*

/**
 * Created by root1 on 2017. 11. 25..
 */
class MainViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){

    lateinit var myPageFragment: MyPageFragment

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MealFragment()
            1 -> ApplyMainFragment()
            2 -> NoticeMainFragment()
            else -> {
                myPageFragment = MyPageFragment()
                myPageFragment
            }
        }
    }

    fun reload(position: Int){
        when(position){
            3 -> myPageFragment.load()
        }
    }

    override fun getCount(): Int {
        return 4
    }

}