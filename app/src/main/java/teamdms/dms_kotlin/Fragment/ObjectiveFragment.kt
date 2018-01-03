package teamdms.dms_kotlin.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_objective.*
import teamdms.dms_kotlin.Activity.SurveyActivity

import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.Activity.MainActivity



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ObjectiveFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ObjectiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObjectiveFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =inflater!!.inflate(R.layout.fragment_objective, container, false)

        var nextButton=view.findViewById<Button>(R.id.button_start_survey)
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
