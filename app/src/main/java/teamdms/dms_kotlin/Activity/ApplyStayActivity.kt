package teamdms.dms_kotlin.Activity

import android.os.*
import com.google.gson.*
import kotlinx.android.synthetic.main.activity_apply_stay.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

/**
 * Created by root1 on 2017. 12. 5..
 */
class ApplyStayActivity: BaseActivity() {

    val radioButtonIdArr = arrayOf(R.id.radio_button_apply_stay_1, R.id.radio_button_apply_stay_2, R.id.radio_button_apply_stay_3, R.id.radio_button_apply_stay_4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_stay)

        init()

        button_apply_stay.setOnClickListener {
            Connector.api.applyStay(getToken(), hashMapOf("value" to when(radio_apply_stay.checkedRadioButtonId){
                radioButtonIdArr[0] -> 1
                radioButtonIdArr[1] -> 2
                radioButtonIdArr[2] -> 3
                else -> 4
            })).enqueue(object : Res<Void>(this){
                override fun callBack(code: Int, body: Void?) {
                    showToast(when(code){
                        201 -> getString(R.string.all_apply_success)
                        204 -> getString(R.string.all_apply_time_fail)
                        else -> "오류 : $code"
                    })
                }
            })
        }
    }

    private fun init(){
        Connector.api.loadStayState(getToken())
                .enqueue(object : Res<JsonObject>(this){
                    override fun callBack(code: Int, body: JsonObject?) {
                        if(code == 200){
                            val stateNum = body!!.get("value").asInt
                            radio_apply_stay.check(radioButtonIdArr[stateNum - 1])
                        }else{ showToast("불러오기 오류") }
                    }
                })
    }
}