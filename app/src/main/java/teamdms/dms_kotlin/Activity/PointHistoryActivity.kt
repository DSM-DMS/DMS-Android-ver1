package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.v7.widget.*
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import kotlinx.android.synthetic.main.activity_notice_list.*
import kotlinx.android.synthetic.main.activity_point_history.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.Connector
import teamdms.dms_kotlin.R
import java.util.*


import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*
import teamdms.dms_kotlin.RecyclerAdapter.*

/**
* Created by root1 on 2017. 12. 5..
*/
class PointHistoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_history)

        recycle_view_point_history.layoutManager= LinearLayoutManager(this)
        recycle_view_point_history.adapter=PointHistoryAdapter()
        loadData()
    }

    private fun loadData(){
        val adapter = recycle_view_point_history.adapter as PointHistoryAdapter
        Connector.api.loadPointHistory(Util.getToken(this))
                .enqueue(object : Res<Array<PointModel>>(this) {
                    override fun callBack(code: Int, body: Array<PointModel>?) {
                        if (code == 200) adapter.setData(body!!)
                    }
                })

    }
}