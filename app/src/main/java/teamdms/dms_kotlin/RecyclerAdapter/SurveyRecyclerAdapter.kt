package teamdms.dms_kotlin.RecyclerAdapter

import android.content.*
import android.support.v7.widget.*
import android.view.*
import android.view.animation.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.*
import teamdms.dms_kotlin.Model.*

/**
 * Created by dsm2017 on 2018-01-02.
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
        holder!!.bindData(title, date, View.OnClickListener{
            intent.putExtra("id",surveies[position].id)
            intent.putExtra("title",surveies[position].title)
            intent.putExtra("date",surveies[position].endDate)
            intent.putExtra("desc",surveies[position].desc)
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

     fun bindData(title: String, date : String, onClick: View.OnClickListener) {
         with(view) {
             text_notice_item_title.text = title
             text_notice_item_date.text = date
             view.setOnClickListener(onClick)
         }
     }
}