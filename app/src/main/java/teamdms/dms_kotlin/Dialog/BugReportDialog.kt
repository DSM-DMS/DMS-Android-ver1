package teamdms.dms_kotlin.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_report_bug.*
import kotlinx.android.synthetic.main.dialog_report_problem.*
import team_dms.dms.Base.Util
import team_dms.dms.Base.Util.getToken
import team_dms.dms.Base.Util.showToast
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Base.CheckValidateActivity
import teamdms.dms_kotlin.R
import javax.security.auth.callback.Callback

/**
 * Created by dsm2017 on 2017-12-28.
 */

class BugReportDialog (context : Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_report_bug)

        button_bug_dialog_cancle.setOnClickListener {dismiss()}

        button_bug_dialog_report.setOnClickListener {

            val content = edit_bug_dialog_content.text.toString().trim()

            if(content.isEmpty()) {
                Util.showToast(context, "값을 다 입력해주세요")
            } else {
                Connector.api.sendBugReport(getToken(context), "Android 오류",content).
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