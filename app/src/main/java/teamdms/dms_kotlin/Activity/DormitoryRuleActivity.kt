package teamdms.dms_kotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dormitory_faq.*
import kotlinx.android.synthetic.main.activity_dormitory_rule.*
import teamdms.dms_kotlin.R

class DormitoryRuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dormitory_rule)
        back()

    }

    private fun back(){
        ib_rule_toolbar_back.setOnClickListener { finish() }
    }
}
