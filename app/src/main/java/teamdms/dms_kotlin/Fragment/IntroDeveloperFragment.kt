package teamdms.dms_kotlin.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_intro_developer.*
import team_dms.dms.Base.Util

import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.R.id.text_intro_developer_content
import teamdms.dms_kotlin.R.id.text_intro_developer_title


class IntroDeveloperFragment : Fragment() {

    private var distinction: Int = 0
    lateinit var rootView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            distinction = arguments.getInt(ARGS_KEY_DISTINCT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_intro_developer,container,false)
        setView()
        return rootView
    }

    private fun setView(){
        var title= rootView.findViewById<TextView>(R.id.text_intro_developer_title)
        title.text=resources.getString(Util.introDeveloperTitle[distinction])
        var content= rootView.findViewById<TextView>(R.id.text_intro_developer_content)
        content.text=resources.getString(Util.introDeveloperContent[distinction])

    }


    companion object {
        private val ARGS_KEY_DISTINCT= "distinction"

        fun newInstance(distinction : Int): IntroDeveloperFragment {
            val fragment = IntroDeveloperFragment()
            val args = Bundle()
            args.putInt(ARGS_KEY_DISTINCT, distinction)
            fragment.arguments = args
            return fragment
        }
    }
}
