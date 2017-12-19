package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_notice_list.view.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import teamdms.dms_kotlin.Fragment.MyPageFragment
import teamdms.dms_kotlin.Model.Notice

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
        // view 세팅

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

        }

    }

}