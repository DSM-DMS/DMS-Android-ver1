package teamdms.dms_kotlin.Activity

import android.os.*
import kotlinx.android.synthetic.main.activity_dormitory_notice.*
import team_dms.dms.Base.*
import teamdms.dms_kotlin.*

class DormitoryNoticeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dormitory_notice)
        back()
    }
    private fun back(){
        ib_notice_toolbar_back.setOnClickListener { finish() }
    }
}
