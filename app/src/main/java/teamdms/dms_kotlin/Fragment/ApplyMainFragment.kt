package teamdms.dms_kotlin.Fragment

import android.os.*
import android.support.v4.app.*
import android.support.v4.content.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_apply_main.view.*
import kotlinx.android.synthetic.main.view_apply_main_top.view.*
import teamdms.dms_kotlin.*


/**
 * Created by dsm2016 on 2017-12-15.
 */

class ApplyMainFragment : Fragment() {

    var rootView: View? = null
    lateinit var inflater: LayoutInflater
    lateinit var initView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater
        rootView = inflater.inflate(R.layout.fragment_apply_main, container, false)
        applyListLoad()
        return rootView
    }

    private fun applyListLoad(){
        with(rootView!!){
//            initView = createBottomView(1)
            expandable_apply_list_layout.addView(createTopView(1), createBottomView(1), true)
            expandable_apply_list_layout.addView(createTopView(2), createBottomView(2))
            expandable_apply_list_layout.addView(createTopView(3), createBottomView(3))
            expandable_apply_list_layout.addView(createTopView(4), createBottomView(4))
//            expandable_apply_list_layout.setInitView(initView)
        }
    }


    private fun createTopView(position: Int): View {
        val view = inflater.inflate(R.layout.view_apply_main_top, null)
        view.setBackgroundColor(ContextCompat.getColor(context, when(position){
            1 -> R.color.colorNo5
            2 -> R.color.colorNo4
            3 -> R.color.colorNo3
            else -> R.color.colorNo2 }))
        with(view){ text_apply_main_top_title.text = when(position){
            1 -> "연장신청"
            2 -> "잔류신청"
            3 -> "외출신청"
            else -> "설문조사"
        }}
        return view
    }

    private fun createBottomView(position: Int): View{
        val view = inflater.inflate(when(position){
            3 -> R.layout.view_apply_main_bottom_going_out
            else -> R.layout.view_apply_main_bottom
        }, null)

        when(position){
            3 -> with(view){

            }
            else -> with(view){

            }
        }

        return view
    }

}


