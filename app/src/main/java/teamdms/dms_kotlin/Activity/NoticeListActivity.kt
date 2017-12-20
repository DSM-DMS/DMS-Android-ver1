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

/**
 * Created by root1 on 2017. 12. 5..
 */
class NoticeListActivity : BaseActivity() {

    var mConfirm : Int? = null
    var notices : Array<Notice>? =null
    var icons = arrayOf(R.drawable.notice_list_icon1,R.drawable.notice_list_icon2,R.drawable.notice_list_icon3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)
        init()
    }

    private fun init(){
        mConfirm=intent.getIntExtra("confirm",0)
        loadData()
        back()
    }

    private fun back(){
        ib_notice_list_back.setOnClickListener { finish() }
    }

    private fun loadData(){
        when(mConfirm){
            0->{
                Connector.api.loadNotice().enqueue(object : Res<JsonArray>(this){
                    override fun callBack(code: Int, body: JsonArray?) {
                        when(code){
                            200->setAdapter(getData(body!!))
                            else -> "오류 $code " }}})
                iv_notice_list_icon.setImageResource(icons[0])
                text_notice_list_title.text="기숙사 규정"
            }
            1->{
                Connector.api.loadRule().enqueue(object : Res<JsonArray>(this){
                    override fun callBack(code: Int, body: JsonArray?) {
                        when(code){
                            200->setAdapter(getData(body!!))
                            else -> "오류 $code " }}})
                iv_notice_list_icon.setImageResource(icons[1])
                text_notice_list_title.text="공지사항"
            }
            2->{
                Connector.api.loadFaq().enqueue(object : Res<JsonArray>(this){
                    override fun callBack(code: Int, body: JsonArray?) {
                        when(code){
                            200->setAdapter(getData(body!!))
                            else -> "오류 $code " }}})
                iv_notice_list_icon.setImageResource(icons[2])
                text_notice_list_title.text="자주하는 질문"
            }
        }
    }

    private fun setAdapter(notice: Array<Notice>)  {
        recycle_view_notice_list.layoutManager = LinearLayoutManager(this)
        recycle_view_notice_list.adapter=NoticesAdapter(this,notice,mConfirm!!)
    }

    private fun getData(jsonArray: JsonArray) : Array<Notice>{
        val gson = GsonBuilder().setPrettyPrinting().create()
        notices =gson.fromJson(jsonArray, object : TypeToken<Array<Notice>>() {}.type)
        return notices!!
    }
}