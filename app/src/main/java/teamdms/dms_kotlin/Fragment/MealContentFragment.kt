package teamdms.dms_kotlin.Fragment

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

class MealContentFragment: Fragment() {

    var rootView: View? = null
    var dateFommater = SimpleDateFormat("YYYY-MM-dd")
    var date: Date = Date()

    public fun setUseDate(date: Date){
        this.date = date
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_meal_content, container, false)
        loadData(dateFommater.format(date))
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
            dateFommater.applyPattern("YYYY")
            yearText.text = dateFommater.format(this@MealContentFragment.date)
            dateFommater.applyPattern("MM월 dd일")
            dateText.text = dateFommater.format(this@MealContentFragment.date)
            dateFommater.applyPattern("EEEE")
            dayStrText.text = dateFommater.format(this@MealContentFragment.date)
        }
    }

    private fun bindMealData(data: Array<String>){
        with(rootView!!){
            breakfastText.text = data[0]
            lunchText.text = data[1]
            dinnerText.text = data[2]
        }
    }


}