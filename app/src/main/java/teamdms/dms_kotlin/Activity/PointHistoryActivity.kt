package teamdms.dms_kotlin.Activity


import android.os.*
import android.support.v7.widget.*
import android.view.*
import kotlinx.android.synthetic.main.activity_point_history.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*
import teamdms.dms_kotlin.RecyclerAdapter.*

/**
* Created by root1 on 2017. 12. 5..
*/
class PointHistoryActivity : BaseActivity() {


    lateinit var mAdapter : PointHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_history)

        recycle_view_point_history.layoutManager= LinearLayoutManager(this)
        mAdapter=PointHistoryAdapter()
        recycle_view_point_history.adapter=mAdapter
        loadData()
    }

    private fun loadData(){
        Connector.api.loadPointHistory(Util.getToken(this))
                .enqueue(object : Res<Array<PointModel>>(this) {
                    override fun callBack(code: Int, body: Array<PointModel>?) {
                        if (code == 200) {
                            mAdapter.setData(body!!)
                            if(mAdapter.PointArr.isEmpty()){
                                text_point_history_no_result.visibility=View.VISIBLE
                            }else{
                                text_point_history_no_result.visibility=View.GONE
                            }
                        }
                    }
                })


    }
}