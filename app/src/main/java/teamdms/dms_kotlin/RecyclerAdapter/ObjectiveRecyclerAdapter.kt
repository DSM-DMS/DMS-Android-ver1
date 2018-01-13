package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.view_objective_item.view.*
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.R

/**
 * Created by dsm2017 on 2018-01-04.
 */

class ObjectiveRecyclerAdapter(var context : Context) : RecyclerView.Adapter<ObjectiveRecyclerAdapter.ViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private lateinit var data : Array<String>
    private var lastCheckPosition : Int = 0 // 마지막으로 체크되어 있었던 라디오 버튼의 포지션
    private var checkedPosition = -1
    private var answer : String = ""

    fun setData (data : Array<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.view_objective_item, parent)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val radioClicked = View.OnClickListener {
            checkedPosition = position
            answer = data[position]
        }

        holder!!.bind(data[position], (position == checkedPosition), radioClicked)
    }

    fun sendAnswer (id : String) {

        Connector.api.sendSurvey(Util.getToken(context), id, answer).enqueue(object : Res<Void>(context){

            override fun callBack(code: Int, body: Void?) {

                when(code) {

                    200 -> Util.showToast(context, "응답이 완료되었습니다.")
                    else -> Util.showToast(context, "응답에 실패하셨습니다. : error "+code.toString())
                }
            }
        })
    }

    class ViewHolder (var view : View) : RecyclerView.ViewHolder(view) {

        var rootView : View = view

        fun bind(question : String, checked : Boolean, radioClicked : View.OnClickListener) { // 응답내용, 라디오버튼 리스너, 라디오 버튼 체크

            with(view) {
                radio_objective_question.text = question
                radio_objective_question.isChecked = true
                view.setOnClickListener(radioClicked)
            }
        }
    }
}
