package teamdms.dms_kotlin.Fragment

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.os.*
import android.support.v4.app.*
import android.util.Log
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_report_problem.*
import kotlinx.android.synthetic.main.fragment_notice_main.view.*
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.NoticeListActivity
import teamdms.dms_kotlin.Dialog.ProblemReportDialog

/**
 * Created by root1 on 2017. 12. 5..
 */

class NoticeMainFragment: Fragment(){

    var rule : Int = 0
    var notice : Int = 1
    var faq : Int = 2

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_notice_main, null)
        next(rootView!!)
        return rootView
    }


    private fun next(rootView : View){

        var intent = Intent(context,NoticeListActivity::class.java)

        with(rootView!!){
            button_notice_main_rule!!.setOnClickListener {
                intent.putExtra("confirm",rule)
                startActivity(intent)
            }
            button_notice_main_question!!.setOnClickListener {
                intent.putExtra("confirm",faq)
                startActivity(intent)
            }
            button_notice_main_notification!!.setOnClickListener {
                intent.putExtra("confirm",notice)
                startActivity(intent)
            }
            button_notice_main_facility!!.setOnClickListener {

                var dialog = ProblemReportDialog(context)
                var layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(dialog.window.attributes)
                layoutParams.width = 1000
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
                dialog.show()
                var window = dialog.window
                window.setLayout(layoutParams.width, layoutParams.height)
            }
        }

    }
}