package team_dms.dms.Model

import com.google.gson.annotations.*
import team_dms.dms.Base.Util

/**
 * Created by root1 on 2017. 11. 23..
 */
class MypageModel {

    @SerializedName("name")
    var name = ""

    @SerializedName("number")
    var number = 10
    //지은이 학번 ? ㅗ 공과 사는 구분하자 병찬아

    @SerializedName("stay_value")
    var stayState: Int = 4

    @SerializedName("extension_11")
    var extension_11: Extension? = null

    @SerializedName("extension_12")
    var extension_12: Extension? = null

    @SerializedName("goingout")
    var goingout: GoingOut? = null

    fun getStudyState(): String {
        return "11 : " + when (extension_11?.classNum) {
            is Int -> "${Util.classNameArr[extension_11!!.classNum!! - 1]}"
            else -> "신청없음"
        } + "\n12 : " + when (extension_12?.classNum) {
            is Int -> "${Util.classNameArr[extension_12!!.classNum!! - 1]}"
            else -> "신청없음"
        }
    }


    fun getStayState(): String {
        val stayStateStrArr = arrayOf("금요귀가", "토요귀가", "토요귀사", "잔류")
        return stayStateStrArr[stayState - 1]
    }


    class Extension {
        @SerializedName("class_num")
        var classNum: Int? = null
        @SerializedName("seat_num")
        var seatNum: Int? = null
    }

    class GoingOut {
        @SerializedName("sat")
        var sat: Boolean = false
        @SerializedName("sun")
        var sun: Boolean = false
    }

}