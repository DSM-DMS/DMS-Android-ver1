package teamdms.dms_kotlin.Activity

import android.os.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_apply_study.*
import team_dms.dms.Base.*
import teamdms.dms_kotlin.*

/**
 * Created by root1 on 2017. 12. 1..
 */
class ApplyStudyActivity: BaseActivity() {

    val tempData = Array<IntArray>(4, { IntArray(4) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_study)

        drawMap()


    }

    private fun drawMap(){
        for (data in tempData){
            linear_apply_study_map.addView(drawMapHorizon(data))
        }
    }

    private fun drawMapHorizon(datas: IntArray): LinearLayout{
        //val width = datas.size * 55 + (datas.size - 1) * 10
        val dataLayout = LinearLayout(this)
        for(data in datas){
            val seatButton = ImageButton(this)
            seatButton.setImageResource(R.drawable.apply_study_icon)
            val layoutParam = LinearLayout.LayoutParams(55, 55)
            layoutParam.setMargins(8,8,8,8)
            dataLayout.addView(seatButton, layoutParam)
        }
        return dataLayout
    }

}