package teamdms.dms_kotlin.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import teamdms.dms_kotlin.Activity.SurveyActivity

import teamdms.dms_kotlin.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NotObjectiveFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NotObjectiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotObjectiveFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
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
        fun newInstance(): NotObjectiveFragment {
            val fragment = NotObjectiveFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
