package teamdms.dms_kotlin.Fragment

import android.content.*
import android.os.*
import android.support.v4.app.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_notice_main.view.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.*


/**
 * Created by root1 on 2017. 12. 5..
 */

class NoticeMainFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_notice_main, null)
        next(rootView!!)
        return rootView
    }


    private fun next(rootView : View) {
        with(rootView) {
            arrayOf(button_notice_main_rule, button_notice_main_notification, button_notice_main_question).let {
                for (i in it.indices)
                    it[i].setOnClickListener {
                        Intent(context, NoticeListActivity::class.java).run {
                            putExtra("confirm", i)
                        }.let { startActivity(it) }
                    }
            }
        }
    }

    companion object {
        fun newInstance(): NoticeMainFragment {
            return NoticeMainFragment()
        }
    }
}