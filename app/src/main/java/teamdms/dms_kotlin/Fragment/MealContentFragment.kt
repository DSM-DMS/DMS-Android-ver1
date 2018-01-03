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
class MealContentFragment: Fragment() {

    var rootView: View? = null
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd",Locale.KOREAN)
    lateinit var date: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        date=Date(arguments.getLong(ARGS_KEY_DATE))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_meal_content, container, false)
        loadData(dateFormat.format(date))
        return rootView
    }

    private fun loadData(date: String){
        Connector.api.loadMeal(date).enqueue(object : Res<MealModel>(activity){
            override fun callBack(code: Int, body: MealModel?) {
                when(code){
                    200 -> bindMealData(body!!.getData())
                    204 -> bindMealData(arrayOf("급식이 없습니다.", "급식이 없습니다.", "급식이 없습니다."))
                    else -> Util.showToast(this@MealContentFragment.context, "오류 : $code")
                }
            }
        })

        with(rootView!!){
            dateFormat.applyPattern("yyyy")
            text_meal_content_year.text = dateFormat.format(this@MealContentFragment.date)
            dateFormat.applyPattern("MM월 dd일")
            text_meal_content_date.text = dateFormat.format(this@MealContentFragment.date)
            dateFormat.applyPattern("EEEE")
            text_meal_content_week.text = dateFormat.format(this@MealContentFragment.date)
        }

    }

    private fun bindMealData(data: Array<String>){
        with(rootView!!){
            text_meal_content_breakfast.text = data[0]
            text_meal_content_lunch.text = data[1]
            text_meal_content_dinner.text = data[2]
        }
    }


    companion object {
        private val ARGS_KEY_DATE= "date"

        fun newInstance(date : Date): MealContentFragment {
            val fragment = MealContentFragment()
            val args = Bundle()
            args.putLong(ARGS_KEY_DATE, date.time)
            fragment.arguments = args
            return fragment
        }
    }
}