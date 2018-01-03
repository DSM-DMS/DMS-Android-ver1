package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_survey.*
import teamdms.dms_kotlin.Fragment.NotObjectiveFragment
import teamdms.dms_kotlin.Fragment.ObjectiveFragment
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.ViewPagerAdapter.SurveyViewPagerAdapter
import android.widget.LinearLayout
import android.support.v4.view.ViewPager





class SurveyActivity : AppCompatActivity() {

    var surveyAdapter : SurveyViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        var view = findViewById<LinearLayout>(R.id.view_survey_count)

        surveyAdapter=SurveyViewPagerAdapter(supportFragmentManager,getFragments())
        view_pager_survey.adapter=surveyAdapter
        view_pager_survey.setPageingScroll(false)
        setView(view,surveyAdapter!!.count,0)


        view_pager_survey.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                setView(view,surveyAdapter!!.count,position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun getFragments() : ArrayList<Fragment>{
        var list : ArrayList<Fragment> = arrayListOf()
        list.add(ObjectiveFragment.newInstance())
        list.add(NotObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())
        list.add(NotObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())
        list.add(NotObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())
        list.add(NotObjectiveFragment.newInstance())

        return list

    }

    fun nextPage(button : Button){
        var maxCount=surveyAdapter!!.count-1
        if(view_pager_survey.currentItem+1==maxCount) {
            button.text="DONE"
            button.setOnClickListener {
                startActivity(Intent(this,SurveyListActivity::class.java))
                finish()
            }
        }else{
            button.text="NEXT"
            button.setOnClickListener {
                view_pager_survey.currentItem = view_pager_survey.currentItem+1
            }
        }
    }

    private fun setView(view: LinearLayout, count: Int, selectNum: Int) {
        view.removeAllViews()
        for (i in 0 until count) {
            val countView = View(applicationContext)
            if (i == selectNum) {
                countView.background = resources.getDrawable(R.drawable.survey_view_shape_selected)
            } else {
                countView.background=(resources.getDrawable(R.drawable.survey_view_shape))
            }
            val layoutParams = LinearLayout.LayoutParams(20, 20)
            layoutParams.setMargins(12, 0, 12, 0)
            view.addView(countView, layoutParams)
        }
    }
}
