package teamdms.dms_kotlin.Activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import team_dms.dms.Base.BaseActivity
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.ViewPagerAdapter.MainViewPagerAdapter

/**
 * Created by root1 on 2017. 11. 25..
 */
class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))

        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager)

    }

}