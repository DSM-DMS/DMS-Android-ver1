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

        button_start_survey_preview.setOnClickListener {
            startActivity(Intent(this,SurveyActivity::class.java))
        }

    }
}