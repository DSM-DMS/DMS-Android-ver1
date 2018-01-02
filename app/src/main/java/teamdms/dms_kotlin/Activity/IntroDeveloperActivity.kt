package teamdms.dms_kotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intro_developer_actvity.*
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.ViewPagerAdapter.IntroViewPagerAdapter

class IntroDeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_developer_actvity)

        view_pager_intro_dev.adapter=IntroViewPagerAdapter(supportFragmentManager)
    }
}
