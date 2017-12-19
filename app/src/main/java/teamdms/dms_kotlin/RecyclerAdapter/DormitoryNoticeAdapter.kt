package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_dormitory_item.view.*
import kotlinx.android.synthetic.main.view_mypage_list_content.view.*
import teamdms.dms_kotlin.Fragment.MyPageFragment
import teamdms.dms_kotlin.Model.Notice
import teamdms.dms_kotlin.R

/**
 * Created by dsm2016 on 2017-12-18.
 */

class DormitoryNoticeAdapter(mContext: Context,items : Array<Notice>,mGroup : Int): RecyclerView.Adapter<DormitoryNoticeAdapter.ViewHolder>() {


    lateinit var context: Context
    lateinit var inflater: LayoutInflater
    lateinit var notices: Array<Notice>
    lateinit var group : Int

    init {
        context=mContext
        notices=items
        group=mGroup
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        inflater = LayoutInflater.from(parent?.context)
        context = parent!!.context


        var view : View?=null
        when(group){
            0-> view=inflater.inflate(R.layout.view_dormitory_item,parent,false)
            1-> view=inflater.inflate(R.layout.view_dormitory_item,parent,false)
            2-> view=inflater.inflate(R.layout.view_dormitory_item,parent,false)
        }

        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        for(notice in notices){
            holder!!.bind(notice.title!!,notice.author!!)
        }
    }

    override fun getItemCount(): Int = notices?.size ?: 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        lateinit var rootView: View

        init { rootView = itemView!! }

        fun bind(title: String,author: String){
            with(rootView){
                dormitory_notice_item_title.text=title
                dormitory_notice_item_author.text=author
            }

        }

    }
}