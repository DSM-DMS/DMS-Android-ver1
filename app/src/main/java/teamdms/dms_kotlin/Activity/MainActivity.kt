package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.design.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import team_dms.dms.Base.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.ViewPagerAdapter.*

/**
 * Created by root1 on 2017. 11. 25..
 */
class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager_main))
        view_pager_main.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))

        view_pager_main.adapter = MainViewPagerAdapter(supportFragmentManager)
        view_pager_main.offscreenPageLimit = 4
    }

    override fun onRestart() {
        super.onRestart()
        view_pager_main.adapter.notifyDataSetChanged()
    }

}