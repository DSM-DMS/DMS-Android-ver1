package teamdms.dms_kotlin.RecyclerAdapter

import android.annotation.SuppressLint
import android.content.*
import android.support.v7.widget.*
import android.view.*
import android.view.animation.*
import android.widget.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*

class PointHistoryAdapter : RecyclerView.Adapter<PointHistoryViewHolder>() {

    lateinit var inflater: LayoutInflater
    lateinit var context: Context
    var PointArr = arrayOf<PointModel>()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PointHistoryViewHolder {
        inflater = LayoutInflater.from(parent!!.context)
        context = parent.context

        val view = inflater.inflate(R.layout.view_point_history_item, null)
        return PointHistoryViewHolder(view)

    }

    override fun onBindViewHolder(holder : PointHistoryViewHolder?, position: Int) {
        val data = PointArr[position!!]

        if(PointArr != null) {
            holder!!.bind(data.reason!!, data.time,data.point!!,data.pointType!!)
            holder.noResultTextView.visibility=View.GONE
            setAnimation(holder.rootView, position)
        }
    }

    override fun getItemCount(): Int = PointArr.size

    fun setData(data: Array<PointModel>) {
        PointArr = data!!.reversedArray()
        notifyDataSetChanged()
    }

    private fun setAnimation(view: View, position: Int) {
        val animation: Animation = if (position % 2 == 0) AnimationUtils.loadAnimation(context, R.anim.slide_left) else AnimationUtils.loadAnimation(context, R.anim.slide_right)
        view.startAnimation(animation)
    }
}

class PointHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var rootView: View

    init {
        rootView = itemView
    }

    var titleTextView: TextView = rootView.findViewById(R.id.text_point_history_title)
    var dateTextView: TextView = rootView.findViewById(R.id.text_point_history_date)
    var resultTextView : TextView = rootView.findViewById(R.id.text_point_history_result)
    var noResultTextView : TextView = rootView.findViewById(R.id.text_point_history_no_result)


    @SuppressLint("ResourceAsColor")
    fun bind(title: String, date: String?, point : Int, boolean: Boolean) {
        with(rootView) {

            titleTextView.text = title
            dateTextView.text = date
            resultTextView.text = point.toString()

            if(boolean){
                titleTextView.setTextColor(R.color.warning)
            }else{
                titleTextView.setTextColor(R.color.colorNo3)
            }
        }
    }
}
