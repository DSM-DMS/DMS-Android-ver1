package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.v7.widget.*
import android.util.*
import com.google.gson.*
import com.google.gson.reflect.*
import kotlinx.android.synthetic.main.activity_notice_list.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*
import teamdms.dms_kotlin.RecyclerAdapter.*

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

        when(mConfirm){
            0->{
                Log.d("mConfirm 0",""+mConfirm)
                iv_noticeList_icon.setImageResource(icons[0])
                text_noticeList_title.text="기숙사 규정"
            }
            1->{
                Log.d("mConfirm 1",""+mConfirm)
                iv_noticeList_icon.setImageResource(icons[1])
                text_noticeList_title.text="공지사항" }
            2->{
                Log.d("mConfirm 2",""+mConfirm)
                iv_noticeList_icon.setImageResource(icons[2])
                text_noticeList_title.text="자주하는 질문" }
            else -> Log.d("mConfirm x",""+mConfirm)


        }
        loadData()

    }

    private fun loadData(){
        when(mConfirm){
            0->{
                Connector.api.loadNotice().enqueue(object : Res<JsonArray>(this){
                    override fun callBack(code: Int, body: JsonArray?) {
                        when(code){
                            200->setAdapter(getData(body!!))
                            else -> "오류 $code " }}})
            }
            1->{
                Connector.api.loadRule().enqueue(object : Res<JsonArray>(this){
                    override fun callBack(code: Int, body: JsonArray?) {
                        when(code){
                            200->setAdapter(getData(body!!))
                            else -> "오류 $code " }}})
            }
            2->{
                Connector.api.loadFaq().enqueue(object : Res<JsonArray>(this){
                    override fun callBack(code: Int, body: JsonArray?) {
                        when(code){
                            200->setAdapter(getData(body!!))
                            else -> "오류 $code " }}})
            }
            else -> Log.d("mConfirm x",""+mConfirm)

        }
    }

    private fun setAdapter(notice: Array<Notice>)  {
        recycleView_noticeList.layoutManager = LinearLayoutManager(this)
        recycleView_noticeList.adapter=NoticesAdapter(this,notice)
    }

    private fun getData(jsonArray: JsonArray) : Array<Notice>{
        val gson = GsonBuilder().setPrettyPrinting().create()
        notices =gson.fromJson(jsonArray, object : TypeToken<Array<Notice>>() {}.type)
        return notices!!
    }

}