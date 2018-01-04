package teamdms.dms_kotlin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import teamdms.dms_kotlin.Activity.SurveyActivity
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.R

class ObjectiveFragment : Fragment() {

    private var survey : SurveyModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle = activity.intent.extras
        //survey = bundle.getSerializable("data") as SurveyModel
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =inflater!!.inflate(R.layout.fragment_not_objective, container, false)

        var nextButton=view.findViewById<Button>(R.id.button_start_survey_not_objective)
        (activity as SurveyActivity).nextPage(nextButton)

        return view
    }

    companion object {
        fun newInstance(): ObjectiveFragment {
            val fragment = ObjectiveFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
