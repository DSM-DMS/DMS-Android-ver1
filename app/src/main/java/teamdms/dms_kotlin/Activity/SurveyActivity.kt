package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
import teamdms.dms_kotlin.Base.BaseFragment
import teamdms.dms_kotlin.Model.SurveyQuestionModel

class SurveyActivity : BaseActivity() {

    var surveyAdapter: SurveyViewPagerAdapter? = null
    var mSelectedPosition = 0
    lateinit var data: Array<SurveyQuestionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        init()
    }

    private fun init() {
        data = intent.getSerializableExtra("question") as Array<SurveyQuestionModel>
        surveyAdapter = SurveyViewPagerAdapter(supportFragmentManager, getFragments((data!!)))


        var id: String = intent.getStringExtra("id") // 설문지 아이디
        var button = findViewById<Button>(R.id.button_start_survey)
        var view = findViewById<LinearLayout>(R.id.view_survey_count) // 설문지 카운터

        setView(view, surveyAdapter!!.count, 0) //view 갯수

        view_pager_survey.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                send(button)
            }
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {
                setView(view, surveyAdapter!!.count, position)
                setButtonText(button, position, surveyAdapter!!.count)
            }
        })
    }

    private fun getFragments(items: Array<SurveyQuestionModel>): ArrayList<BaseFragment> { // Fragment에 데이터를 넣은채로 보내줌
        var list: ArrayList<BaseFragment> = arrayListOf()
        for (item in items) {
            var b = Bundle()
            b.putSerializable("data", item)
            list.add(Fragment.instantiate(this, isObjective(item).javaClass.name, b) as BaseFragment)
        }
        return list
    }

    private fun isObjective(surveyQuestionModel: SurveyQuestionModel): Fragment { // 객관식인지 주관식인지 판단함
        return if (surveyQuestionModel.isObjective) ObjectiveFragment.newInstance()
        else NotObjectiveFragment.newInstance()
    }

    private fun send(button: Button) {
        var fragment = surveyAdapter!!.getItem(mSelectedPosition) as BaseFragment
        var lastPage = surveyAdapter!!.count

        when (mSelectedPosition) {
            lastPage - 1 -> {
                button.setOnClickListener {
                    if (fragment.sendAnswer()) {
                        var handler = Handler()
                        handler.postDelayed(finishDelayRun, 2000) //2초
                    }
                }
            }
            else -> {
                button.setOnClickListener {
                    if(fragment.sendAnswer()){
                        var handler = Handler()
                        handler.postDelayed(delayRun, 2000) //2초
                    }
                }
            }
        }
    }

    fun setButtonText(button: Button, currentCount: Int, maxCount: Int) { // 다음 버튼을눌렸을때
        mSelectedPosition = currentCount
        if (currentCount + 1 == maxCount) {
            button.text = "DONE"
        } else {
            button.text = "NEXT"
        }
    }

    private val delayRun = Runnable {
        view_pager_survey.currentItem = view_pager_survey.currentItem + 1
    }

    private val finishDelayRun = Runnable {
        Util.showToast(this,"설문조사가 끝났습니다")
        startActivity(Intent(this, SurveyListActivity::class.java))
        finish()
    }

    private fun setView(view: LinearLayout, count: Int, selectNum: Int) { // count를 하는 함수
        view.removeAllViews()
        for (i in 0 until count) {
            val countView = View(applicationContext)
            if (i == selectNum) {
                countView.background = resources.getDrawable(R.drawable.survey_view_shape_selected)
            } else {
                countView.background = (resources.getDrawable(R.drawable.survey_view_shape))
            }
            LinearLayout.LayoutParams(20, 20).let { layoutParams ->
                layoutParams.setMargins(12, 0, 12, 0)
                view.addView(countView, layoutParams)
            }
        }
    }
}
