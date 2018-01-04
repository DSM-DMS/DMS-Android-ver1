package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.os.*
import kotlinx.android.synthetic.main.activity_preview_survey.*
import team_dms.dms.Base.*
import teamdms.dms_kotlin.*

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

        button_start_survey_preview.setOnClickListener {
            var intent =Intent(this,SurveyActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }

    }
}