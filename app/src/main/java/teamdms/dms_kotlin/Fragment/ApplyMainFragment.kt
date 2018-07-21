package teamdms.dms_kotlin.Fragment

import android.content.*
import android.os.*
import android.support.v4.app.*
import android.support.v4.content.*
import android.support.v7.app.AlertDialog
import android.text.Layout
import android.view.*
import kotlinx.android.synthetic.main.fragment_apply_main.view.*
import kotlinx.android.synthetic.main.view_apply_main_bottom.view.*
import kotlinx.android.synthetic.main.view_apply_main_bottom_going_out.view.*
import kotlinx.android.synthetic.main.view_apply_main_top.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
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

    private fun setContentView() {
        with(rootView) {
            expandable_apply_list_layout.addView(createTopView(1), createContentView(1), true)
            expandable_apply_list_layout.addView(createTopView(2), createContentView(2))
            expandable_apply_list_layout.addView(createTopView(3), createContentView(3))
            expandable_apply_list_layout.addView(createTopView(4), createContentView(4))
        }
    }

    fun load() {
        with(contentViewArr[0]) { text_apply_main_content.text = "안녕" }
        with(contentViewArr[1]) { text_apply_main_content.text = "안녕" }
        with(contentViewArr[2]) {
            switch_apply_main_sat.isChecked = false
            switch_apply_main_sun.isChecked = false
        }
        with(contentViewArr[3]) { text_apply_main_content.text = "새로운 기능을 알려주세요" }

        Connector.api.loadApplyInfo(Util.getToken(context))
                .enqueue(object : Res<MypageModel>(context) {
                    override fun callBack(code: Int, body: MypageModel?) {
                        when (code) {
                            200 -> body.let {
                                with(contentViewArr[0]) {
                                    if (body != null) text_apply_main_content.text = body!!.getStudyState() else {
                                        text_apply_main_content.text = "안녕"
                                    }
                                }
                                with(contentViewArr[1]) {
                                    if (body != null) text_apply_main_content.text = "신청 : ${body!!.getStayState()}" else {
                                        text_apply_main_content.text = "안녕"
                                    }
                                }
                                with(contentViewArr[2]) {
                                    if (body != null) {
                                        switch_apply_main_sat.isChecked = body!!.goingout!!.sat!!
                                        switch_apply_main_sun.isChecked = body!!.goingout!!.sun!!
                                    } else {
                                    }
                                }
                            }
                            403 -> Util.showToast(context, "재로그인 하세요")
                        }
                    }
                })
    }

    private fun createTopView(position: Int): View {
        val view = inflater.inflate(R.layout.view_apply_main_top, null)
        with(view) {
            setBackgroundColor(ContextCompat.getColor(context, when (position) {
                1 -> R.color.colorNo4
                2 -> R.color.colorNo3
                3 -> R.color.colorNo2
                else -> R.color.colorNo1
            }))

            text_apply_main_top_title.text = when (position) {
                1 -> "연장신청"
                2 -> "잔류신청"
                3 -> "외출신청"
                else -> "새로운 기능"
            }
        }
        return view
    }

    private fun createContentView(position: Int): View {
        val view = inflater.inflate(when (position) {
            3 -> R.layout.view_apply_main_bottom_going_out
            else -> R.layout.view_apply_main_bottom
        }, null)

        when (position) {
            3 -> with(view) {
                with(view) {
                    button_apply_main_apply_going_out.setOnClickListener {
                        Connector.api.applyOut(Util.getToken(context), hashMapOf(
                                "sat" to switch_apply_main_sat.isChecked,
                                "sun" to switch_apply_main_sun.isChecked))
                                .enqueue(object : Res<Void>(context) {
                                    override fun callBack(code: Int, body: Void?) {
                                        Util.showToast(context, when (code) {
                                            201 -> context.getString(R.string.all_apply_success)
                                            204 -> "신청 가능 시간이 아닙니다."
                                            else -> "신청 실패"
                                        })
                                    }
                                })
                    }
                }
            }
            else -> with(view) {
                with(view) {
                    image_apply_main_content_image.setImageResource(when (position) {
                        1 -> R.drawable.apply_study_icon
                        2 -> R.drawable.apply_stay_icon
                        else -> R.drawable.apply_survey_icon
                    })
                    button_apply_main_apply.setOnClickListener {
                        if (Util.getToken(context) != "JWT ") {
                            when (position) {
                                1 -> context.startActivity(Intent(context, ApplyStudyActivity::class.java))
                                2 -> context.startActivity(Intent(context, ApplyStayActivity::class.java))
                                else -> {
                                    alert{
                                        title = "DMS의 새로운 기능"
                                        message = "DMS 페이스북 메신저를 통해 알려주세요.\nDMS가 의견을 참고하여 개발하겠습니다\n감사합니다."
//                                        customView {
//                                            verticalLayout {
//                                                textView("DMS 페이스북 메신저를 통해 알려주세요. ").textSize = 10F
//                                                textView("DMS가 의견을 참고하여 개발하겠습니다.").textSize = 10F
//                                                textView("감사합니다"){
//                                                    textSize = 10F
//                                                }
//                                            }
//                                        }
                                        yesButton {  }
                                    }.show()
                                    /*AlertDialog.Builder(context)
                                            .setTitle("당신이 원하는 DMS의 새로운 기능")
                                            .setMessage("DMS 페이스북 메신저를 통해 알려주세요. \nDMS가 의견을 참고하여 개발하겠습니다 \n감사합니다.")
                                            .setPositiveButton("확인") { p0, p1 -> }
                                            .show()*/
                                }
                            }
//                            context.startActivity(Intent(context, when (position) {
//                                1 -> ApplyStudyActivity::class.java
//                                2 -> ApplyStayActivity::class.java
//                                else -> SurveyListActivity::class.java
//                            }))
                        } else {
                            Util.showToast(context, "로그인을 해주세요")
                        }
                    }
                }
            }
        }

        view.setBackgroundColor(ContextCompat.getColor(context, when (position) {
            1 -> R.color.colorNo4
            2 -> R.color.colorNo3
            3 -> R.color.colorNo2
            else -> R.color.colorNo1
        }))

        contentViewArr.add(view)
        return view

    }

    companion object {
        fun newInstance(): ApplyMainFragment {
            return ApplyMainFragment()
        }
    }

}


