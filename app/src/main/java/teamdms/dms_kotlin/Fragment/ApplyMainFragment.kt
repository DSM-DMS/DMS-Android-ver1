package teamdms.dms_kotlin.Fragment

import android.content.*
import android.os.*
import android.support.v4.app.*
import android.support.v4.content.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_apply_main.view.*
import kotlinx.android.synthetic.main.view_apply_main_bottom.view.*
import kotlinx.android.synthetic.main.view_apply_main_bottom_going_out.view.*
import kotlinx.android.synthetic.main.view_apply_main_top.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import team_dms.dms.Model.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.*


/**
 * Created by dsm2016 on 2017-12-15.
 */

class ApplyMainFragment : Fragment() {

    lateinit var rootView: View
    lateinit var inflater: LayoutInflater
    var contentViewArr = arrayListOf<View>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.inflater = inflater
        rootView = inflater.inflate(R.layout.fragment_apply_main, container, false)
        setContentView()
        return rootView
    }

    private fun setContentView(){
        with(rootView){
            expandable_apply_list_layout.addView(createTopView(1), createContentView(1), true)
            expandable_apply_list_layout.addView(createTopView(2), createContentView(2))
            expandable_apply_list_layout.addView(createTopView(3), createContentView(3))
            expandable_apply_list_layout.addView(createTopView(4), createContentView(4))
        }
    }

    fun load(){
        with(contentViewArr[0]){ text_apply_main_content.text = "안녕" }
        with(contentViewArr[1]){ text_apply_main_content.text = "안녕" }
        with(contentViewArr[2]){
            switch_apply_main_sat.isChecked = false
            switch_apply_main_sun.isChecked = false
        }
        with(contentViewArr[3]){ text_apply_main_content.text = "의견을 제출하세요" }

        Connector.api.loadMyInfo(Util.getToken(context))
                .enqueue(object : Res<MypageModel>(context){
                    override fun callBack(code: Int, body: MypageModel?) {
                        when(code){
                            200->  body.let {
                                with(contentViewArr[0]) {
                                    if(body != null) text_apply_main_content.text = body!!.getStudyState() else {text_apply_main_content.text="안녕"}
                                }
                                with(contentViewArr[1]) {
                                    if (body != null) text_apply_main_content.text = "신청 : ${body!!.getStayState()}" else{text_apply_main_content.text="안녕"}
                                }
                                with(contentViewArr[2]) {
                                    if (body != null){
                                        switch_apply_main_sat.isChecked = body!!.goingout!!.sat!!
                                        switch_apply_main_sun.isChecked = body!!.goingout!!.sun!!
                                    }else{}
                                }
                            }
                            403-> Util.showToast(context,"재로그인 하세요")
                        }
                    }
                })
    }

    private fun createTopView(position: Int): View {
        val view = inflater.inflate(R.layout.view_apply_main_top, null)
        view.setBackgroundColor(ContextCompat.getColor(context, when(position){
            1 -> R.color.colorNo4
            2 -> R.color.colorNo3
            3 -> R.color.colorNo2
            else -> R.color.colorNo1 }))
        with(view){ text_apply_main_top_title.text = when(position){
            1 -> "연장신청"
            2 -> "잔류신청"
            3 -> "외출신청"
            else -> "설문조사"
        }}
        return view
    }

    private fun createContentView(position: Int): View{
        val view = inflater.inflate(when(position){
            3 -> R.layout.view_apply_main_bottom_going_out
            else -> R.layout.view_apply_main_bottom
        }, null)

        when(position){
            3 -> with(view){
                with(view){
                    button_apply_main_apply_going_out.setOnClickListener {
                        Connector.api.applyOut(Util.getToken(context), switch_apply_main_sat.isChecked, switch_apply_main_sun.isChecked)
                                .enqueue(object : Res<Void>(context){
                                    override fun callBack(code: Int, body: Void?) {
                                        Util.showToast(context, when(code){
                                            201 -> context.getString(R.string.all_apply_success)
                                            204 -> "신청 가능 시간이 아닙니다."
                                            else -> "신청 실패"
                                        })
                                    }
                                })
                    }
                }
            }
            else -> with(view){
                with(view){
                    image_apply_main_content_image.setImageResource(when(position){
                        1 -> R.drawable.apply_study_icon
                        2 -> R.drawable.apply_stay_icon
                        else -> R.drawable.apply_survey_icon
                    })
                    button_apply_main_apply.setOnClickListener {
                        if(Util.getToken(context) != "JWT "){
                            context.startActivity(Intent(context, when(position){
                                1 -> ApplyStudyActivity::class.java
                                2 -> ApplyStayActivity::class.java
                                else -> SurveyListActivity::class.java
                            }))
                        }else{ Util.showToast(context, "로그인을 해주세요") }
                    }
                }
            }
        }

        view.setBackgroundColor(ContextCompat.getColor(context, when(position){
            1 -> R.color.colorNo4
            2 -> R.color.colorNo3
            3 -> R.color.colorNo2
            else -> R.color.colorNo1 }))

        contentViewArr.add(view)
        return view

    }

    companion object {
        fun newInstance(): ApplyMainFragment {
            return ApplyMainFragment()
        }
    }

}


