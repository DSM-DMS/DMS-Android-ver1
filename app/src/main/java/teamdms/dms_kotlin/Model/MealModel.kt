package team_dms.dms.Model

import com.google.gson.annotations.*

/**
 * Created by root1 on 2017. 11. 23..
 */
class MealModel {

    @SerializedName("breakfast")
    lateinit var breakfast: Array<String>

    @SerializedName("lunch")
    lateinit var lunch: Array<String>

    @SerializedName("dinner")
    lateinit var dinner: Array<String>

    fun getData(): Array<String>{
        fun arrToStr(arr: Array<String>): String{
            var sendStr = ""
            for (i in arr){
                sendStr += "$i, "
            }
            return sendStr.removeSuffix(", ")
        }
        return arrayOf(arrToStr(breakfast), arrToStr(lunch), arrToStr(dinner))
    }

}