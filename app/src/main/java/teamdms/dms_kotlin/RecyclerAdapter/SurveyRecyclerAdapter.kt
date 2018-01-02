package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_notice_list.view.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import team_dms.dms.Base.Util
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.R

/**
 * Created by dsm2017 on 2018-01-02. 문제점 : onClickListenr가 제대로 구현 안됨, retrofit에서 넘어오는 데이터가 없음
 */
class SurveyRecyclerAdapter (context : Context) : RecyclerView.Adapter<SurveyRecyclerViewHolder>() {

    lateinit var context : Context
    lateinit var data : Array<SurveyModel>
    private val layoutInflater = LayoutInflater.from(context)

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SurveyRecyclerViewHolder {
        val view = layoutInflater.inflate(R.layout.view_notice_item, parent)
        val viewHolder = SurveyRecyclerViewHolder(view)
        return viewHolder
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SurveyRecyclerViewHolder?, position: Int) {

        val title = data[position].title
        val date = data[position].startDate + " ~ " + data[position].endDate
        val onClick = View.OnClickListener{Util.showToast(context,"operation the onClickListener")}
        holder!!.bindData(title, date, onClick)
    }

    fun setSurveyData (data : Array<SurveyModel>) {
        this.data = data
    }
}

class SurveyRecyclerViewHolder (var view : View): RecyclerView.ViewHolder(view) {

     fun bindData(title: String, date : String, onClick : View.OnClickListener) {

         with(view) {
             text_notice_item_title.text = title
             text_notice_item_date.text = date
             view.setOnClickListener(onClick)
         }
     }
}