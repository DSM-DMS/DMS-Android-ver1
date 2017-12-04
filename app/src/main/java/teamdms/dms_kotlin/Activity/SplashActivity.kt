package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.design.widget.*
import team_dms.dms.Base.*
import teamdms.dms_kotlin.*

/**
 * Created by root1 on 2017. 11. 28..
 */
class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_study)
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()

        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(R.layout.view_apply_study_bottom_sheet)
    }
}