package teamdms.dms_kotlin.Fragment

import android.content.Intent
import android.os.*
import android.support.v4.app.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_notice_main.*
import kotlinx.android.synthetic.main.fragment_notice_main.view.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.DormitoryFaqActivity
import teamdms.dms_kotlin.Activity.DormitoryNoticeActivity
import teamdms.dms_kotlin.Activity.DormitoryRuleActivity
import teamdms.dms_kotlin.R.id.button_notice_main_rule

/**
 * Created by root1 on 2017. 12. 5..
 */

class NoticeMainFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_notice_main, null)
        next(rootView!!)
        return rootView
    }


    private fun next(rootView : View){
        with(rootView!!){
            button_notice_main_rule!!.setOnClickListener { startActivity(Intent(activity,DormitoryRuleActivity::class.java)) }
            button_notice_main_question!!.setOnClickListener { startActivity(Intent(activity,DormitoryFaqActivity::class.java)) }
            button_notice_main_notification!!.setOnClickListener { startActivity(Intent(activity,DormitoryNoticeActivity::class.java)) }
            button_notice_main_facility!!.setOnClickListener { }
        }

    }

}