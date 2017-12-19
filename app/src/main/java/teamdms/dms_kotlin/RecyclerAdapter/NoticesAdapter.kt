package teamdms.dms_kotlin.RecyclerAdapter

import android.content.*
import android.support.v7.widget.*
import android.view.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import teamdms.dms_kotlin.Fragment.MyPageFragment
import teamdms.dms_kotlin.Model.Notice
import teamdms.dms_kotlin.R

/**
 * Created by dsm2016 on 2017-12-18.
 */

class NoticesAdapter(context: Context,notices : Array<Notice>): RecyclerView.Adapter<NoticesAdapter.ViewHolder>() {


    lateinit var mContext: Context
    lateinit var inflater: LayoutInflater
    lateinit var mNotices: Array<Notice>

    init {
        this.mContext=context
        this.mNotices=notices
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        inflater = LayoutInflater.from(parent?.context)
        mContext = parent!!.context

        var view : View?=null
        view= View.inflate(mContext, R.layout.view_notice_item,parent)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        for(notice in mNotices){
            holder!!.bind(notice.title!!,notice.author!!)
        }
    }

    override fun getItemCount(): Int = mNotices?.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        lateinit var rootView : View

        init { rootView = itemView!! }

        fun bind(title: String,author: String){
            with(rootView){
                text_notice_item_title.text=title
                text_notice_author.text=author
            }


            rootView.setOnClickListener {

            }
        }

    }

}