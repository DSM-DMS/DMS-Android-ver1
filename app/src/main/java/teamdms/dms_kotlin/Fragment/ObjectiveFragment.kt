/**
 * Created by dsm2017 on 2018-03-10.
 */
package teamdms.dms_kotlin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_objective.*
import teamdms.dms_kotlin.Base.BaseFragment
import teamdms.dms_kotlin.Model.SurveyQuestionModel
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.RecyclerAdapter.ObjectiveRecyclerAdapter

class ObjectiveFragment : BaseFragment() {

    lateinit var survey : SurveyQuestionModel
    lateinit var mAdapter : ObjectiveRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survey = arguments.getSerializable("data") as SurveyQuestionModel
        mAdapter = ObjectiveRecyclerAdapter(context, survey.id)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_objective, container, false)
        var titleTextView=view.findViewById<TextView>(R.id.text_survey_title_objective)
        titleTextView.text = survey.title

        mAdapter.apply {
            setData(survey.choices)
            setHasStableIds(true)
        }

        var recycler=view.findViewById<RecyclerView>(R.id.recycler_objective_survey)
        recycler.apply {
            adapter = mAdapter
            LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }.let { linearLayoutManager -> layoutManager = linearLayoutManager }
        }
        return view
    }

    override fun sendAnswer(): Boolean {
        mAdapter.sendAnswer()
        return true
    }

    companion object {
        fun newInstance(): ObjectiveFragment {
            return ObjectiveFragment()
        }
    }
}