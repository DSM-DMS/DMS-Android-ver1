package teamdms.dms_kotlin.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_notice_list.*
import kotlinx.android.synthetic.main.fragment_intro_developer.*
import team_dms.dms.Base.Util

import teamdms.dms_kotlin.R
import teamdms.dms_kotlin.R.id.text_intro_developer_title
import teamdms.dms_kotlin.RecyclerAdapter.IntroDeveloperAdapter
import teamdms.dms_kotlin.RecyclerAdapter.NoticesAdapter


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

        val viewpager : RecyclerView = rootView.findViewById(R.id.iv_intro_developer_viewpager)
        viewpager.layoutManager = LinearLayoutManager(activity)
        viewpager.adapter = IntroDeveloperAdapter(distinction)
        setView()
        return rootView
    }

    private fun setView(){
        var title= rootView.findViewById<TextView>(R.id.text_intro_developer_title)
        var icon=rootView.findViewById<ImageView>(R.id.iv_intro_developer_icon)

        title.text=resources.getString(Util.introDeveloperTitle[distinction])
        //content.text=resources.getString(Util.introDeveloperContent[distinction])
        icon.setImageResource(Util.introDeveloperIcon[distinction])

        var linear = rootView.findViewById<LinearLayout>(R.id.intro_back_linear)
        if(distinction%2==0){
            linear.setBackgroundResource(Util.introBacks[0])
            title.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }else{
            linear.setBackgroundResource(Util.introBacks[1])
            title.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        }
    }

    companion object {
        private val ARGS_KEY_DISTINCT = "distinction"

        fun newInstance(distinction: Int): IntroDeveloperFragment {
            val args = Bundle().apply {
                putInt(ARGS_KEY_DISTINCT, distinction)
            }
            IntroDeveloperFragment().let { introDeveloperFragment ->
                introDeveloperFragment.arguments = args
                return introDeveloperFragment
            }
        }
    }
}
