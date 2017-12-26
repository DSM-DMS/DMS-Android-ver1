package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_notice_detail.*
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Model.Notice
import teamdms.dms_kotlin.R

class NoticeDetail : AppCompatActivity() {

    var icon= arrayOf(R.drawable.notice_rule_icon,R.drawable.notice_notification_icon,R.drawable.notice_facility_icon)
    var mConfirm : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)
        init()
    }

    private fun init(){
        var noticeID=intent.getStringExtra("noticeID")
        mConfirm=intent.getIntExtra("confirm",0)
        loadData(noticeID)
    }

    private fun loadData(noticeID: String) {
        Connector.api.loadNotice_detail(Util.notice[mConfirm!!], noticeID).enqueue(object : Res<Notice>(this) {
            override fun callBack(code: Int, body: Notice?) {
                when(code){
                    200-> web_view_notice_detail.loadData(body!!.content, "text/html; charset=utf-8", "UTF-8")
                    500->"오류 : $code"
                }
            }
        })

        ib_notice_detail_icon.setImageResource(Util.noticeImages[mConfirm!!])
        tv_notice_detail_title.text=Util.noticeTitles[mConfirm!!]

    }
}


