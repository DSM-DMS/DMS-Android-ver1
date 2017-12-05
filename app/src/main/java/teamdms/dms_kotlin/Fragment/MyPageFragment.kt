package teamdms.dms_kotlin.Fragment

import android.os.*
import android.support.v4.app.*
import android.support.v7.widget.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import team_dms.dms.Model.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.RecyclerAdapter.*

/**
 * Created by root1 on 2017. 11. 30..
 */
class MyPageFragment: Fragment() {

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_mypage, null)
        return  rootView
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init(){
        with(rootView!!){
            recycler_mypage.layoutManager = LinearLayoutManager(activity)
            recycler_mypage.adapter = MyPageRecyclerAdapter().setTextViews(text_mypage_study_state, text_mypage_stay_state)
        }

        Connector.api.loadMyInfo(Util.getToken(activity)).enqueue(object : Res<MypagelModel>(activity){
            override fun callBack(code: Int, body: MypagelModel?) {
                if(code == 200){
                    bind(body!!.getStudyState(), body!!.getStayState())
                }else{ Util.showToast(context, "불러오기 실패") }
            }
        })

    }

    private fun bind(studyState: String, stayState: String){
        with(rootView!!){
            text_mypage_study_state.text = studyState
            text_mypage_stay_state.text = stayState
        }
    }


}