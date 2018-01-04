package teamdms.dms_kotlin.RecyclerAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_objective_item.view.*
import teamdms.dms_kotlin.Model.SurveyModel
import teamdms.dms_kotlin.Model.SurveyQuestionModel
import teamdms.dms_kotlin.R
import java.util.zip.Inflater

/**
 * Created by dsm2017 on 2018-01-04.
 */

class ObjectiveRecyclerAdapter(var context : Context) : RecyclerView.Adapter<ViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private lateinit var data : SurveyQuestionModel
    private var radioChecked : Boolean = false // 라디오 버튼 체크가 하나만 될 수 있게 하기 위해
    private var lastCheckPosition : Int = 0 // 마지막으로 체크되어 있었던 라디오 버튼의 포지션

    fun setData (data : SurveyQuestionModel) {
        this.data = data
        no
    }

    override fun getItemCount(): Int {
        return data.choices!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.view_objective_item, parent)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        with(holder!!.view) {

            if(radio_objective_question.isChecked) {

                if(radioChecked) {


                } else {


                }
            }
        }
    }
}

class ViewHolder (var view : View) : RecyclerView.ViewHolder(view) {

    var rootView : View = view

    fun setData(question : String) {

        with(view) {
            radio_objective_question.text = question
        }
    }
}