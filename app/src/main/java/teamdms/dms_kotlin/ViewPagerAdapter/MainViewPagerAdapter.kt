package teamdms.dms_kotlin.ViewPagerAdapter

import android.support.v4.app.*
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import teamdms.dms_kotlin.Fragment.*
import teamdms.dms_kotlin.R
import android.R.attr.fragment
import android.support.v4.app.ActivityCompat.setEnterSharedElementCallback
import android.view.View


/**
 * Created by root1 on 2017. 11. 25..
 */
class MainViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){

    var fragmentManager : FragmentManager = fm
    lateinit var myPageFragment: MyPageFragment
    lateinit var applyMainFragment: ApplyMainFragment
    lateinit var noticeMainFragment : NoticeMainFragment

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MealFragment()
            1 -> {
                applyMainFragment = ApplyMainFragment.newInstance()
                applyMainFragment
            }
            2 -> {
                noticeMainFragment= NoticeMainFragment.newInstance()
                noticeMainFragment

            }
            else -> {
                myPageFragment = MyPageFragment.newInstance()
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