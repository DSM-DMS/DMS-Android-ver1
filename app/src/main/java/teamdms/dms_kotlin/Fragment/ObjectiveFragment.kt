/**
 * Created by dsm2017 on 2018-03-10.
 */
package teamdms.dms_kotlin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_objective.*
import team_dms.dms.Base.Util
import teamdms.dms_kotlin.Activity.SurveyActivity
import teamdms.dms_kotlin.Base.BaseFragment
import teamdms.dms_kotlin.Model.SurveyQuestionModel
import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.RecyclerAdapter.ObjectiveRecyclerAdapter
import android.view.WindowManager


class ObjectiveFragment : BaseFragment() ,ObjectiveRecyclerAdapter.RadioClickListener {

    lateinit var survey : SurveyQuestionModel
    lateinit var adapter : ObjectiveRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survey = arguments.getSerializable("data") as SurveyQuestionModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =inflater!!.inflate(R.layout.fragment_objective, container, false)

        var titleTextView=view.findViewById<TextView>(R.id.text_survey_title_objective)
        titleTextView.text=survey.title
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter = ObjectiveRecyclerAdapter(context,survey.id)
        adapter.setOnItemClickListener(this)
        adapter.setData(survey.choices)
        adapter.setHasStableIds(true)


        var recyclerView = view.findViewById<RecyclerView>(R.id.recycler_objective_survey)
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = layoutManager
        return view
    }
    override fun onRadioClickListener(position: Int, view: View) {
        adapter.selectedRadio()
    }

    override fun sendAnswer() : Boolean {
        adapter.sendAnswer()
        return true
    }

    companion object {
        fun newInstance(): ObjectiveFragment {
            return ObjectiveFragment()
        }
    }

}