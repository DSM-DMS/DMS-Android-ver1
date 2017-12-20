package teamdms.dms_kotlin.Dialog

import android.app.*
import android.content.*
import android.os.*
import android.text.*
import kotlinx.android.synthetic.main.dialog_report_problem.*
import team_dms.dms.Base.*
import team_dms.dms.Base.Util.getToken
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

/**
 * Created by dsm2017 on 2017-12-20.
 */

class ProblemReportDialog (context :Context) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_report_problem)

        button_problem_dialog_cancle.setOnClickListener { dismiss() } // 기본 값 세팅
        checkValidate()

        edit_problem_dialog_content.addTextChangedListener(object : TextWatcher{ // 지속적으로 유효성 확인
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkValidate()
            }
        })

        edit_problem_dialog_title.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkValidate()
            }
        })

        edit_problem_dialog_roomnumber.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkValidate()
            }
        })
    }

    private fun checkValidate () { // 항목이 비어있는지 안비어있는지에 대해 체크한다

        val title : String = edit_problem_dialog_title.text.toString().trim()
        val room : String  =  edit_problem_dialog_roomnumber.text.toString().trim()
        val content : String = edit_problem_dialog_content.text.toString().trim()

        button_problem_dialog_report.setOnClickListener {
            if(!title.isEmpty() && !room.isEmpty() && !content.isEmpty()) {
                Connector.api.reportProblem(getToken(context), title, Integer.parseInt(room), content)
                        .enqueue(object : Res<Void> (context) {

                            override fun callBack(code: Int, body: Void?) {
                                Util.showToast(context, when(code){
                                    201 -> "신고 성공"
                                    else -> "신고 오류 : $code"
                                })
                                dismiss()
                            }
                        })
            }
            else { Util.showToast(context, "값을 다 입력하세요") }
        }
    }
}


