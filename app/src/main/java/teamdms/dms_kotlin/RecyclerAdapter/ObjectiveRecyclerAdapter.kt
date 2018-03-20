package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_objective.view.*
import kotlinx.android.synthetic.main.view_objective_item.view.*
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.R

/**
 * Created by dsm2017 on 2018-01-04.
 */

class ObjectiveRecyclerAdapter(var context : Context, var id : String) : RecyclerView.Adapter<ObjectiveRecyclerAdapter.ViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private lateinit var data : Array<String>
    var answer : String = ""

    companion object {
        var checkedPosition = -1
        var sClickListener: RadioClickListener? = null
    }

    fun setData (data : Array<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun selectedRadio() {
        notifyDataSetChanged()
    }

    fun clearRadio(){
        checkedPosition=-1
    }

    fun setOnItemClickListener(clickListener: RadioClickListener) {
        sClickListener = clickListener
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.view_objective_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val radioClicked = View.OnClickListener {
            answer = data[position]
            checkedPosition = holder!!.adapterPosition
            sClickListener!!.onRadioClickListener(holder!!.adapterPosition, holder.rootView)
        }

        Log.d("radiobutton count","count"+position)
        holder!!.radioButton.isChecked = position==checkedPosition
        holder!!.bind(data[position], radioClicked)
    }

    fun sendAnswer() {
        Connector.api.sendSurvey(Util.getToken(context), id,answer).enqueue(object : Res<Void>(context) {
            override fun callBack(code: Int, body: Void?) {
                Util.showToast(context,answer)
                when (code) {
                    201 -> Util.showToast(context, "응답이 완료되었습니다.")
                    204 -> Util.showToast(context, "존재하지 않는 질문입니다. : error " + code.toString())
                    else -> Util.showToast(context, "서버오류." + code.toString())
                }
            }
        })

    }

    class ViewHolder (var view : View) : RecyclerView.ViewHolder(view) {

        var rootView : View = view
        var radioButton = rootView.findViewById<RadioButton>(R.id.radio_objective_question)

        fun bind(question : String,onClick: View.OnClickListener) { // 응답내용, 라디오버튼 리스너, 라디오 버튼 체크
            with(view) {
                radioButton.text = question
                radioButton.setOnClickListener(onClick)
            }
        }
    }

    interface RadioClickListener{
        fun onRadioClickListener(position: Int, view: View)
    }

}
