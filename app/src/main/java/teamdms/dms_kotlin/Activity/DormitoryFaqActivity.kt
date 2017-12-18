package teamdms.dms_kotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dormitory_faq.*
import team_dms.dms.Connect.Connector
import teamdms.dms_kotlin.R

class DormitoryFaqActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dormitory_faq)
        back()
    }

    private fun back(){
        ib_faq_toolbar_back.setOnClickListener { finish() }
    }


    private fun loadData(){
        Connector.api.load
    }

    private fun setAdpater(){

    }
}
