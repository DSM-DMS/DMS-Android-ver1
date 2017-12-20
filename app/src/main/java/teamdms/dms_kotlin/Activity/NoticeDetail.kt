package teamdms.dms_kotlin.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_notice_detail.*
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Model.Notice
import teamdms.dms_kotlin.R

class NoticeDetail : AppCompatActivity() {

    var icon= arrayOf(R.drawable.notice_rule_icon,R.drawable.notice_notification_icon,R.drawable.notice_facility_icon)
    var mConfirm : Int? = null
    var notice : Notice? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)
    }



    fun init(){


    }

    fun loadData(noticeID : String) {

        when(mConfirm){
            0->{
                Connector.api.loadRule_detail(noticeID).enqueue(object : Res<JsonObject>(this){
                    override fun callBack(code: Int, body: JsonObject?) {
                        when(code){
                            200->notice=getNoticeData(body!!.asJsonObject)
                            else -> "오류 $code " }}})

                ib_notice_detail_icon.setImageResource(icon[0])
                tv_notice_detail_title.text="기숙사 규정"
            }
            1->{
                Connector.api.loadNotice_detail(noticeID).enqueue(object : Res<JsonObject>(this){
                    override fun callBack(code: Int, body: JsonObject?) {
                        when(code){
                            200->notice=getNoticeData(body!!.asJsonObject)
                            else -> "오류 $code " }}})
                ib_notice_detail_icon.setImageResource(icon[1])
                tv_notice_detail_title.text="공지사항"
            }
            2->{
                Connector.api.loadFag_detail(noticeID).enqueue(object : Res<JsonObject>(this) {
                    override fun callBack(code: Int, body: JsonObject?) {
                        when (code) {
                            200 -> notice = getNoticeData(body!!.asJsonObject)
                            else -> "오류 $code " }}})
                ib_notice_detail_icon.setImageResource(icon[2])
                tv_notice_detail_title.text="자주 하는 질문"
            }

        }
    }

    fun getNoticeData(jsonObject: JsonObject): Notice {
        val gson = GsonBuilder().setPrettyPrinting().create()
        var data = gson.fromJson<Notice>(jsonObject, Notice::class.java!!)
        return data
    }


}
