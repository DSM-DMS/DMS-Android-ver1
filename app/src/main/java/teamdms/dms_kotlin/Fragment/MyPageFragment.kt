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
        init()
        return  rootView
    }

    override fun onStart() {
        super.onStart()
        load()
    }

    private fun init(){
        with(rootView!!){
            recycler_mypage.layoutManager = LinearLayoutManager(activity)
            recycler_mypage.adapter = MyPageRecyclerAdapter(this@MyPageFragment)
        }
    }

    fun load(){
        Connector.api.loadMyInfo(Util.getToken(activity)).enqueue(object : Res<MypagelModel>(activity){
            override fun callBack(code: Int, body: MypagelModel?) {
                setStateData(body)
            }
        })
    }

    fun setStateData(data: MypagelModel?){
        with(rootView!!){
            if(data != null){
                text_mypage_study_state.text = data!!.getStudyState()
                text_mypage_stay_state.text = data!!.getStayState()
                text_mypage_merit.text = "0"
                text_mypage_demerit.text = "0"
            }else{
                text_mypage_study_state.text = "오류"
                text_mypage_stay_state.text = "오류"
                text_mypage_merit.text = "0"
                text_mypage_demerit.text = "0"
            }
        }
    }


}