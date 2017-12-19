package teamdms.dms_kotlin.Activity

import android.os.*
import kotlinx.android.synthetic.main.activity_dormitory_faq.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

class DormitoryFaqActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dormitory_faq)
        back()
    }

    private fun back(){
        ib_faq_toolbar_back.setOnClickListener { finish() }
    }


    private fun loadData(){
        Connector.api.loadFaq()
    }

    private fun setAdpater(){

    }
}
