package teamdms.dms_kotlin.Activity

import android.content.*
import android.os.*
import android.support.v7.widget.*
import android.util.Log
import kotlinx.android.synthetic.main.activity_preview_survey.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*
import teamdms.dms_kotlin.RecyclerAdapter.*

/**
 * Created by root1 on 2017. 12. 19..
 */
class SurveyPreviewActivity : BaseActivity() {

    private lateinit var data : Array<SurveyQuestionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_survey)

        var desc = intent.getStringExtra("desc")
        var date = intent.getStringExtra("date")
        var id = intent.getStringExtra("id")

        text_survey_preview_date.text = "종료일 : " + date
        text_view_survey_content.text = desc

        loadSurvey(id, this)

        button_start_survey_preview.setOnClickListener {
            if(data.isEmpty()){
                var intent =Intent(this,SurveyListActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                var intent =Intent(this,SurveyActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("question", data)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadSurvey(id : String, context: Context) {
        Connector.api.loadSurvey_detail(getToken(), id).enqueue(object : Res<Array<SurveyQuestionModel>>(this){
            override fun callBack(code: Int, body: Array<SurveyQuestionModel>?) {
                when(code){
                    200-> {
                        recycler_preview_survey.adapter = SurveyPreviewRecyclerAdapter(context, body!!)
                        recycler_preview_survey.layoutManager = LinearLayoutManager(context)
                        data = body!!
                    }
                }
            }
        })
    }
}