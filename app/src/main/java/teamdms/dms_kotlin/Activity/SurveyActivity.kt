package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_survey.*
import teamdms.dms_kotlin.Fragment.NotObjectiveFragment
import teamdms.dms_kotlin.Fragment.ObjectiveFragment
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.ViewPagerAdapter.SurveyViewPagerAdapter

class SurveyActivity : AppCompatActivity() {

    var surveyAdapter : SurveyViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        surveyAdapter=SurveyViewPagerAdapter(supportFragmentManager,getFragments())
        view_pager_survey.adapter=surveyAdapter
        view_pager_survey.setPageingScroll(false)
    }

    private fun getFragments() : ArrayList<Fragment>{
        var list : ArrayList<Fragment> = arrayListOf()
        list.add(ObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())
        list.add(NotObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())
        list.add(NotObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())
        list.add(NotObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())
        list.add(ObjectiveFragment.newInstance())

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
            button.text="START"
            button.setOnClickListener {
                view_pager_survey.currentItem = view_pager_survey.currentItem+1
            }
        }
    }
}
