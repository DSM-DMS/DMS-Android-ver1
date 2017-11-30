package teamdms.dms_kotlin.Fragment

import android.annotation.*
import android.os.*
import android.support.v4.app.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_meal_content.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import team_dms.dms.Model.*
import teamdms.dms_kotlin.*
import java.text.*
import java.util.*

/**
 * Created by root1 on 2017. 11. 26..
 */

@SuppressLint("ValidFragment")
class MealContentFragment(date: Date): Fragment() {

    var rootView: View? = null
    val dateFormater = SimpleDateFormat("YYYY-MM-dd")
    var date: Date = Date()

    init {
        dateFormater.timeZone = TimeZone.getTimeZone("ko-KR")
        this.date = date
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_meal_content, container, false)
        loadData(dateFormater.format(date))
        return rootView
    }

    private fun loadData(date: String){
        Connector.api.loadMeal(date).enqueue(object : Res<MealModel>(this.activity){
            override fun callBack(code: Int, body: MealModel?) {
                when(code){
                    200 -> bindMealData(body!!.getData())
                    204 -> bindMealData(arrayOf("급식이 없습니다.", "급식이 없습니다.", "급식이 없습니다."))
                    else -> Util.showToast(this@MealContentFragment.context, "오류 : $code")
                }
            }
        })

        with(rootView!!){
            dateFormater.applyPattern("YYYY")
            text_meal_content_year.text = dateFormater.format(this@MealContentFragment.date)
            dateFormater.applyPattern("MM월 dd일")
            text_meal_content_date.text = dateFormater.format(this@MealContentFragment.date)
            dateFormater.applyPattern("EEEE")
            text_meal_content_week.text = dateFormater.format(this@MealContentFragment.date)
        }

    }

    private fun bindMealData(data: Array<String>){
        with(rootView!!){
            text_meal_content_breakfast.text = data[0]
            text_meal_content_lunch.text = data[1]
            text_meal_content_dinner.text = data[2]
        }
    }


}