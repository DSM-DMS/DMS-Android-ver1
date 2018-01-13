package teamdms.dms_kotlin.Activity

import android.content.Context
import android.content.Intent
import android.os.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_preview_survey.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.SurveyQuestionModel
import teamdms.dms_kotlin.RecyclerAdapter.SurveyPreviewRecyclerAdapter

/**
 * Created by root1 on 2017. 12. 19..
 */
class SurveyPreviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_survey)

        var desc = intent.getStringExtra("desc")
        var date = intent.getStringExtra("date")
        var id = intent.getStringExtra("id")

        text_survey_preview_date.text=date
        text_view_survey_content.text=desc

        loadSurvey(id, this)

        button_start_survey_preview.setOnClickListener {
            var intent =Intent(this,SurveyActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun loadSurvey(id : String, context: Context) {
        var items = arrayOf<SurveyQuestionModel>()
        Connector.api.loadSurvey_detail(getToken(), id).enqueue(object : Res<Array<SurveyQuestionModel>>(this){
            override fun callBack(code: Int, body: Array<SurveyQuestionModel>?) {
                when(code){
                    200-> {
                        recycler_preview_survey.adapter = SurveyPreviewRecyclerAdapter(context, body!!)
                        recycler_preview_survey.layoutManager = LinearLayoutManager(context)
                    }
                }
            }

        })
    }
}