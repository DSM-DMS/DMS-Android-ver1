package teamdms.dms_kotlin.Activity

import android.os.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_dormitory_notice.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Model.Notice
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.R.id.ib_faq_toolbar_back

/**
 * Created by root1 on 2017. 12. 5..
 *
 */
class NoticeListActivity: BaseActivity() {


    lateinit var notices : Array<Notice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dormitory_notice)
        back()
    }


    private fun back(){
        ib_notice_toolbar_back.setOnClickListener { finish() }
    }


    private fun loadData(){
        Connector.api.loadFaq().enqueue(object : Res<JsonArray>(this){
            override fun callBack(code: Int, body: JsonArray?) {
                when(code){
                    200->getData(body!!)
                    else-> Util.showToast(context,"서버오류")
                }

            }


        })
    }

    private fun getData(jsonArray: JsonArray) : Array<Notice>{
        val gson = GsonBuilder().setPrettyPrinting().create()
        notices =gson.fromJson(jsonArray, object : TypeToken<Array<Notice>>() {}.type)
        return notices
    }

}