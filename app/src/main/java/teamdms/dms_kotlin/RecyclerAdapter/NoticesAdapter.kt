package teamdms.dms_kotlin.RecyclerAdapter

import android.content.*
import android.support.v7.widget.*
import android.view.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import teamdms.dms_kotlin.Activity.NoticeDetail
import teamdms.dms_kotlin.Fragment.MyPageFragment
import teamdms.dms_kotlin.Model.Notice
import teamdms.dms_kotlin.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by dsm2016 on 2017-12-18.
 */

class NoticesAdapter(context: Context,confirm : Int): RecyclerView.Adapter<NoticesAdapter.ViewHolder>() {


    lateinit var mContext: Context
    lateinit var inflater: LayoutInflater
    var mConfirm : Int? = null
    var mNotices = ArrayList<Notice>()

    init {
        this.mContext=context
        this.mConfirm=confirm
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        inflater = LayoutInflater.from(parent?.context)
        var view = inflater.inflate(R.layout.view_notice_item, null)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        var intent = Intent(mContext, NoticeDetail::class.java)

        holder!!.bind(mNotices[position].title!!, mNotices[position].author!!, {
            intent.putExtra("confirm", mConfirm)
            intent.putExtra("noticeID",mNotices[position].id)
            mContext.startActivity(intent)
        })


    }

    override fun getItemCount(): Int = mNotices!!.size

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        lateinit var rootView : View

        init { rootView = itemView!! }

        fun bind(title: String,author: String,onClick: (Any) -> Unit){
            with(rootView){
                text_notice_item_title.text=title
                text_notice_author.text=author
                ib_notice_next.setOnClickListener(onClick)
            }
        }
    }

    fun setData(notices : ArrayList<Notice>){
        mNotices = notices
        notifyDataSetChanged()
    }

}