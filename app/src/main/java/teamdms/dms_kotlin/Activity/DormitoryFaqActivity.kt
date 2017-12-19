package teamdms.dms_kotlin.Activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_dormitory_faq.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.R

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
        Connector.api.loadFaq().enqueue(object : Res<JsonArray>(this){
            override fun callBack(code: Int, body: JsonArray?) {
                when(code){

                }


            }


        })
    }

//    private fun setAdpater() : DormitoryNoticeAdapter{
//
//
//    }
}
