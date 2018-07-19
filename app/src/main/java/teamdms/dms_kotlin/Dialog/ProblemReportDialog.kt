package teamdms.dms_kotlin.Dialog

import android.app.*
import android.content.*
import android.os.*
import android.text.*
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.Gravity
import android.view.Window
import android.view.animation.Animation
import kotlinx.android.synthetic.main.dialog_report_problem.*
import team_dms.dms.Base.*
import team_dms.dms.Base.Util.getToken
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*


/**
 * Created by dsm2017 on 2017-12-20.
 */

class ProblemReportDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_report_problem)
        button_problem_dialog_cancle.setOnClickListener { dismiss() } // 기본 값 세팅

        button_problem_dialog_report.setOnClickListener {

            val title: String = edit_problem_dialog_title.text.toString().trim()
            val room: String = edit_problem_dialog_roomnumber.text.toString().trim()
            val content: String = edit_problem_dialog_content.text.toString().trim()

            if (title.isEmpty() || room.isEmpty() || content.isEmpty()) {
                Util.showToast(context, "값을 다 입력하세요")
            } else {
                Connector.api.reportProblem(getToken(context), hashMapOf(
                        "content" to "$title , $content",
                        "room" to room.toInt()))
                        .enqueue(object : Res<Void>(context) {
                            override fun callBack(code: Int, body: Void?) {
                                Util.showToast(context, when (code) {
                                    201 -> "신고 성공"
                                    else -> "신고 오류 : $code"
                                })
                            }
                        })
                dismiss()
            }
        }
    }
}


