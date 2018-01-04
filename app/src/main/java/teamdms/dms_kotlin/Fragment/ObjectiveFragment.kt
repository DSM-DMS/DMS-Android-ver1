package teamdms.dms_kotlin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_objective.*
import teamdms.dms_kotlin.Activity.SurveyActivity
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.Model.SurveyQuestionModel
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.RecyclerAdapter.ObjectiveRecyclerAdapter

class ObjectiveFragment : Fragment() {

    lateinit var survey : SurveyQuestionModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle = activity.intent.extras
        survey = bundle.getSerializable("data") as SurveyQuestionModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =inflater!!.inflate(R.layout.fragment_objective, container, false)

        var nextButton=view.findViewById<Button>(R.id.button_start_survey_not_objective)
        (activity as SurveyActivity).nextPage(nextButton)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val adapter = ObjectiveRecyclerAdapter(context)
        adapter.setData(survey)

        recycler_objective_survey.adapter = adapter
        recycler_objective_survey.layoutManager = layoutManager

        return view
    }

    companion object {
        fun newInstance(): ObjectiveFragment {
            return ObjectiveFragment()
        }
    }
}
