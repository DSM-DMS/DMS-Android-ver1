package teamdms.dms_kotlin.Activity

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notice_list.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import retrofit2.Call
import retrofit2.Response
import team_dms.dms.Base.BaseActivity
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.RecyclerAdapter.SurveyRecyclerAdapter

/**
 * Created by dsm2017 on 2018-01-02.
 */
class SurveyListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)


        loadData(this)
    }

    private fun loadData(context : Context) { // Toast 사용을 위해 context를 받음
        text_notice_list_title.text = Util.noticeTitles[3]
        iv_notice_list_icon.setImageResource(R.drawable.apply_survey_icon)

        val adapter = SurveyRecyclerAdapter()
        recycle_view_notice_list.adapter = adapter
        recycle_view_notice_list.layoutManager = LinearLayoutManager(this)

        Connector.api.loadSurvey(getToken()).enqueue(object : Res<Array<SurveyModel>> (this) {

            override fun callBack(code: Int, body: Array<SurveyModel>?) {
                when(code) {
                    200 -> adapter.setSurveyData(body!!)
                }
            }
        })
    }
}