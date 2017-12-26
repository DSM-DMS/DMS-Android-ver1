package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.os.*
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_notice_detail.*
import kotlinx.android.synthetic.main.activity_notice_list.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Model.Notice
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.RecyclerAdapter.NoticesAdapter
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by root1 on 2017. 12. 5..
 */
class NoticeListActivity : BaseActivity() {

    var mConfirm : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)
        init()
    }

    private fun init(){
        mConfirm=intent.getIntExtra("confirm",0)
        loadData()
    }

    private fun loadData(){
                Connector.api.loadNotice(Util.notice[mConfirm!!]).enqueue(object : Res<ArrayList<Notice>>(this){
                    override fun callBack(code: Int, body: ArrayList<Notice>?) {
                        when(code){
                            200->{
                                setAdapter(reverse(body!!))
                            }
                            500->"오류 : $code"
                        }}})
        iv_notice_list_icon.setImageResource(Util.noticeIcons[mConfirm!!])
        text_notice_list_title.text=Util.noticeTitles[mConfirm!!]
    }

    private fun setAdapter(notice : ArrayList<Notice>)  {
        var noticeAdapter=NoticesAdapter(this@NoticeListActivity,mConfirm!!)
        recycle_view_notice_list.layoutManager = LinearLayoutManager(this)
        recycle_view_notice_list.adapter=noticeAdapter
        noticeAdapter.setLoadData(notice)
    }

    private fun reverse(notices : ArrayList<Notice>) : ArrayList<Notice>{
        var list=notices
        Collections.reverse(list)
        return list
    }
}