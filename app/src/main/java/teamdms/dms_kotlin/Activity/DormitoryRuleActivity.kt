package teamdms.dms_kotlin.Activity

import android.os.*
import kotlinx.android.synthetic.main.activity_dormitory_rule.*
import team_dms.dms.Base.*
import teamdms.dms_kotlin.*

class DormitoryRuleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dormitory_rule)
        back()

    }

    private fun back(){
        ib_rule_toolbar_back.setOnClickListener { finish() }
    }
}
