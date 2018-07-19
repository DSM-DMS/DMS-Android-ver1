package teamdms.dms_kotlin.Dialog

import android.app.*
import android.content.*
import android.os.*
import android.view.*
import kotlinx.android.synthetic.main.dialog_report_bug.*
import team_dms.dms.Base.Util.getToken
import team_dms.dms.Base.Util.showToast
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

/**
 * Created by dsm2017 on 2017-12-28.
 */

class BugReportDialog (context : Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_report_bug)

        button_bug_dialog_cancle.setOnClickListener {dismiss()}

        button_bug_dialog_report.setOnClickListener {

            val content = edit_bug_dialog_content.text.toString().trim()

            if(content.isEmpty()) {
                showToast(context, "값을 다 입력해주세요")
            } else {
                Connector.api.sendBugReport(getToken(context), hashMapOf("platform" to 2, "content" to content)).
                        enqueue(object : Res<Void> (context) {
                            override fun callBack(code: Int, body: Void?) {
                                when(code) {
                                    201 -> {
                                        showToast(context, "신고 완료했습니다,")
                                    }
                                    else -> showToast(context, "신고에 실패했습니다.")
                                }
                            }
                        })
                dismiss()
            }
        }
    }
}