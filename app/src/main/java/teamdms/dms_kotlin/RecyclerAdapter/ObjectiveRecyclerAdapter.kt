package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.R
import java.util.zip.Inflater

/**
 * Created by dsm2017 on 2018-01-04.
 */

class ObjectiveRecyclerAdapter(var context : Context) : RecyclerView.Adapter<ViewHolder>() {

    val inflater : LayoutInflater = LayoutInflater.from(context)
    private lateinit var data : SurveyModel

    fun setData (data : SurveyModel) {

    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = inflater.inflate(R.layout.view_objective_item, parent)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }
}

class ViewHolder (var view : View) : RecyclerView.ViewHolder(view) {


}