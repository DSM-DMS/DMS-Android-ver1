package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.v7.app.*
import kotlinx.android.synthetic.main.activity_notice_detail.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*

class NoticeDetailActivity : AppCompatActivity() {

    var iconArr= arrayOf(R.drawable.notice_rule_icon, R.drawable.notice_notification_icon, R.drawable.notice_facility_icon)
    var confirm = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)
        init()
    }

    private fun init(){
        val noticeID = intent.getStringExtra("noticeID")
        confirm = intent.getIntExtra("confirm",0)
        loadData(noticeID)
    }

    private fun loadData(noticeID : String) {
        ib_notice_detail_icon.setImageResource(iconArr[confirm])
        tv_notice_detail_title.text = Util.noticeTitles[confirm]

        Connector.api.loadNotice_detail(Util.noticeIDs[confirm], noticeID)
                .enqueue(object : Res<NoticeModel>(this){
                    override fun callBack(code: Int, body: NoticeModel?) {
                        if (code == 200){
                            web_view_notice_detail.loadData(body!!.content, "text/html; charset=utf-8", "UTF-8")
                            tv_notice_detail_title.text = body!!.title
                        }
                        else Util.showToast(context, "오류 : $code")
                    }
                })

    }
}
