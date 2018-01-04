package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_notice_list.view.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import team_dms.dms.Base.Util
import teamdms.dms_kotlin.Activity.NoticeDetailActivity
import teamdms.dms_kotlin.Activity.SurveyActivity
import teamdms.dms_kotlin.Activity.SurveyPreviewActivity
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.R

/**
 * Created by dsm2017 on 2018-01-02. 문제점 : onClickListenr가 제대로 구현 안됨, retrofit에서 넘어오는 데이터가 없음
 */
class SurveyRecyclerAdapter : RecyclerView.Adapter<SurveyRecyclerViewHolder>() {

    lateinit var context : Context
    var surveies = arrayOf<SurveyModel>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SurveyRecyclerViewHolder {
        context=parent!!.context
        var inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.view_notice_item, null)
        return SurveyRecyclerViewHolder(view)
    }

    override fun getItemCount() = surveies.size

    override fun onBindViewHolder(holder: SurveyRecyclerViewHolder?, position: Int) {
        val intent = Intent(context, SurveyPreviewActivity::class.java)

        val title = surveies[position].title
        val date = surveies[position].startDate + " ~ " + surveies[position].endDate
        holder!!.bindData(title, date, {
            context.startActivity(intent)
        })
        setAnimation(holder.view, position)
    }

    private fun setAnimation (view: View, position: Int) {
        val animation : Animation = if(position%2==0) AnimationUtils.loadAnimation(context, R.anim.slide_right) else AnimationUtils.loadAnimation(context, R.anim.slide_left)
        view.startAnimation(animation)
    }

    fun setSurveyData (data : Array<SurveyModel>) {
        surveies = data.reversedArray()
        notifyDataSetChanged()
    }
}

class SurveyRecyclerViewHolder (var view : View): RecyclerView.ViewHolder(view) {

     fun bindData(title: String, date : String, onClick: (Any) -> Unit) {
         with(view) {
             text_notice_item_title.text = title
             text_notice_item_date.text = date
             view.setOnClickListener(onClick)
         }
     }
}