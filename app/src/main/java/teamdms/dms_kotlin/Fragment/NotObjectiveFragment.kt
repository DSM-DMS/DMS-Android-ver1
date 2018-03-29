package teamdms.dms_kotlin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_singup.*
import kotlinx.android.synthetic.main.fragment_not_objective.*
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Activity.SurveyActivity
import teamdms.dms_kotlin.Base.BaseFragment
import teamdms.dms_kotlin.Base.CheckValidateActivity
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.Model.SurveyQuestionModel
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.RecyclerAdapter.ObjectiveRecyclerAdapter

class NotObjectiveFragment : BaseFragment() {

    lateinit var survey: SurveyQuestionModel
    lateinit var rootView : View
    var answer : String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survey = arguments.getSerializable("data") as SurveyQuestionModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView =inflater!!.inflate(R.layout.fragment_not_objective, container, false)
        var titleTextView=rootView.findViewById<TextView>(R.id.text_survey_title_not_objective)
        titleTextView.text=survey.title
        return rootView
    }

    override fun sendAnswer() : Boolean {
        var editText = rootView!!.findViewById<EditText>(R.id.edit_survey_answer)

        return if (editText.text.isEmpty()){
            Util.showToast(activity,"값을 다 입력하세요.")
            false
        }else{
            answer=editText.text.toString()
            send(answer)
            true
        }
    }

    private fun send(answer : String) {
        Connector.api.sendSurvey(Util.getToken(context), survey.id,answer).enqueue(object : Res<Void>(context) {
            override fun callBack(code: Int, body: Void?) {
                when (code) {
                    201 -> Util.showToast(context, "응답이 완료되었습니다.")
                    204 -> Util.showToast(context, "존재하지 않는 질문입니다. : error " + code.toString())
                    403 -> Util.showToast(context, "권한이 없습니다." + code.toString())
                    else -> Util.showToast(context, "서버오류." + code.toString())
                }
            }
        })
    }

    companion object {
        fun newInstance(): NotObjectiveFragment {
            return NotObjectiveFragment()
        }
    }
}
