package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_survey.*
import teamdms.dms_kotlin.Fragment.ObjectiveFragment
import teamdms.dms_kotlin.Fragment.NotObjectiveFragment
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.ViewPagerAdapter.SurveyViewPagerAdapter
import android.widget.LinearLayout
import android.support.v4.view.ViewPager
import android.util.Log
import team_dms.dms.Base.BaseActivity
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.Model.SurveyQuestionModel

class SurveyActivity : BaseActivity() {

    var surveyAdapter : SurveyViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        var id : String = intent.getStringExtra("id") // 설문지 아이디
        var view = findViewById<LinearLayout>(R.id.view_survey_count) // 설문지 카운터
        var data = intent.getSerializableExtra("question") as Array<SurveyQuestionModel>

        surveyAdapter=SurveyViewPagerAdapter(supportFragmentManager,getFragments((data!!)))
        view_pager_survey.adapter=surveyAdapter
        view_pager_survey.setPageingScroll(false)
        setView(view,surveyAdapter!!.count,0)

        view_pager_survey.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                setView(view,surveyAdapter!!.count,position)
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    private fun getFragments(items : Array<SurveyQuestionModel>) : ArrayList<Fragment>{ // Fragment에 데이터를 넣은채로 보내줌
        var list : ArrayList<Fragment> = arrayListOf()

        for(item in items){

            var b = Bundle()
            b.putSerializable("data", item)
            list.add(Fragment.instantiate(this,isObjective(item).javaClass.name,b))
            Log.v("surveyTest", "non null 1")
         }

        return list
    }

    private fun isObjective(surveyQuestionModel: SurveyQuestionModel): Fragment { // 객관식인지 주관식인지 판단함
        return if (surveyQuestionModel.isObjective) ObjectiveFragment.newInstance()
        else NotObjectiveFragment.newInstance()
    }

    fun nextPage(button : Button){ // 페이지 넘기기
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

    private fun setView(view: LinearLayout, count: Int, selectNum: Int) { // count를 하는 함수
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
