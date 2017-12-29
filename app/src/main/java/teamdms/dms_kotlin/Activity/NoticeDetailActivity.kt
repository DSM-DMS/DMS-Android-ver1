package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.transition.Explode
import android.support.v7.app.*
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_notice_detail.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*

class NoticeDetailActivity : AppCompatActivity() {

    var confirm = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)
        init()
    }

    private fun init(){
        val noticeID = intent.getStringExtra("noticeID")
        confirm = intent.getIntExtra("confirm",0)
        title_view_notice_detail.text=intent.getStringExtra("noticeTitle")
        //setWindowAnimations()
        loadData(noticeID)
    }

    private fun loadData(noticeID : String) {
        ib_notice_detail_icon.setImageResource(Util.noticeImages[confirm])

        Connector.api.loadNotice_detail(Util.noticeIDs[confirm], noticeID)
                .enqueue(object : Res<NoticeModel>(this){
                    override fun callBack(code: Int, body: NoticeModel?) {
                        if (code == 200){
                            web_view_notice_detail.loadData(body!!.content, "text/html; charset=utf-8", "UTF-8")
                        }
                        else Util.showToast(context, "오류 : $code")
                    }
                })
    }

    private fun setWindowAnimations(){
        //이동 애니메이션 구현
        val slideTransition = Fade(4)
        slideTransition.duration=500L
        this.window.enterTransition=slideTransition
        this.window.exitTransition=slideTransition
    }

}
