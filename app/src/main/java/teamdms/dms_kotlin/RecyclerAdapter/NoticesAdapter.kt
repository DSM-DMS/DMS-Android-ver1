package teamdms.dms_kotlin.RecyclerAdapter

import android.content.*
import android.support.v7.widget.*
import android.view.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.*
import teamdms.dms_kotlin.Model.*

/**
 * Created by dsm2016 on 2017-12-18.
 */

class NoticesAdapter(confirm : Int): RecyclerView.Adapter<NoticeViewHolder>() {

    lateinit var inflater: LayoutInflater
    lateinit var context: Context
    var noticeArr = arrayOf<NoticeModel>()
    var confirm : Int = 0

    init { this.confirm = confirm }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val view = inflater.inflate(R.layout.view_notice_item, null)
        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val intent = Intent(context, NoticeDetailActivity::class.java)
        val data = noticeArr[position]
        holder.bind(data.title, data.write_date, {
            intent.putExtra("confirm", confirm)
            intent.putExtra("noticeID",data.id)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int = noticeArr.size

    public fun setData(data: Array<NoticeModel>){
        noticeArr = data
        notifyDataSetChanged()
    }

}

class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var rootView : View

    init { rootView = itemView!! }

    fun bind(title: String, author: String, onClick: (Any) -> Unit){
        with(rootView){
            text_notice_item_title.text = title
            text_notice_item_date.text = author
            card_notice_item.setOnClickListener(onClick)
        }
    }

}