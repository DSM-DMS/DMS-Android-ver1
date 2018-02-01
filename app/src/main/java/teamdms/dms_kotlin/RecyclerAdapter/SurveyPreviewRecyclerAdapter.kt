package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.databinding.adapters.ViewGroupBindingAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_survey_preview_item.view.*
import teamdms.dms_kotlin.Model.SurveyQuestionModel
import teamdms.dms_kotlin.R

/**
 * Created by dsm2017 on 2018-01-13.
 */

class SurveyPreviewRecyclerAdapter(val context : Context, val question : Array<SurveyQuestionModel>) : RecyclerView.Adapter<PreviewViewHolder> () {

    val inflate : LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: PreviewViewHolder?, position: Int) {

        var title = question[position].title
        holder!!.bind(title)
    }

    override fun getItemCount(): Int =  question.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PreviewViewHolder {

        val view = inflate.inflate(R.layout.view_survey_preview_item, parent, false)
        return PreviewViewHolder(view)
    }
}

class PreviewViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

    fun bind(title : String) {

        with(view) {

            text_survey_preview_question.text = title
        }
    }
}