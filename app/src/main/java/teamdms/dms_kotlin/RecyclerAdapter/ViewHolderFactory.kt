package teamdms.dms_kotlin.RecyclerAdapter

import android.support.v7.widget.RecyclerView
import android.view.View
import teamdms.dms_kotlin.R

/**
 * Created by dsm2016 on 2018-01-02.
 */
object ViewHolderFactory{

    var resID : Int? = 0

    fun getLayoutID(type : Int): Int {
        return if(type==4){
            resID=R.layout.view_survey_preview_item
            resID!!
        }else{
            resID=R.layout.view_notice_item
            resID!!
        }
    }

    fun getViewHolder(confirm : Int,view : View)  : RecyclerView.ViewHolder {
        return NoticeViewHolder(view)
    }
}