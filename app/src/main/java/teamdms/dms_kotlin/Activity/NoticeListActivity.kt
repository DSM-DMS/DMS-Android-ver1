package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.v7.widget.*
import kotlinx.android.synthetic.main.activity_notice_list.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.Connector
import teamdms.dms_kotlin.R
import java.util.*


import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*
import teamdms.dms_kotlin.RecyclerAdapter.*

/**
 * Created by root1 on 2017. 12. 5..
 */
class NoticeListActivity : BaseActivity() {
    var confirm: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)
        confirm = intent.getIntExtra("confirm", 0)
        recycle_view_notice_list.layoutManager = LinearLayoutManager(this)
        recycle_view_notice_list.adapter = NoticesAdapter(confirm)
        loadData()
    }

    private fun loadData() {
        iv_notice_list_icon.setImageResource(Util.noticeIcons[confirm])
        text_notice_list_title.text = Util.noticeTitles[confirm]
        val adapter = recycle_view_notice_list.adapter as NoticesAdapter
        Connector.api.loadNotice(Util.noticeIDs[confirm])
                .enqueue(object : Res<Array<NoticeModel>>(this) {
                            override fun callBack(code: Int, body: Array<NoticeModel>?) {
                                if (code == 200) {
                                    adapter.setData(body!!)
                                }}})
    }
}