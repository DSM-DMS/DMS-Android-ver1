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
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.R

/**
 * Created by dsm2017 on 2018-01-02.
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
        return SurveyRecyclerViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SurveyRecyclerViewHolder?, position: Int) {
        val intent = Intent(context, SurveyActivity::class.java)

        val title = data[position].title
        val date = data[position].startDate + " ~ " + data[position].endDate
        val onClick = View.OnClickListener{
            Util.showToast(context,"operation the onClickListener")
            context.startActivity(intent)
        }
        holder!!.bindData(title, date, onClick)
        setAnimation(holder.view, position)
    }

    private fun setAnimation (view: View, position: Int) {
        val animation : Animation = if(position%2==0) AnimationUtils.loadAnimation(context, R.anim.slide_right) else AnimationUtils.loadAnimation(context, R.anim.slide_left)
        view.startAnimation(animation)
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