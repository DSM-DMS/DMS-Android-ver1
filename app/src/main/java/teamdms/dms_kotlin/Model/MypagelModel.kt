package team_dms.dms.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by root1 on 2017. 11. 23..
 */
class MypagelModel {

    @SerializedName("extension_11_class")
    var class11Num: Int? = null

    @SerializedName("extension_12_class")
    var class12Num: Int? = null

    @SerializedName("extension_11_seat")
    var seat11Num: Int? = null

    @SerializedName("extension_12_seat")
    var seat12Num: Int? = null

    @SerializedName("goingout_sat")
    var outSatState= false

    @SerializedName("goingout_sun")
    var outSunState= false

    @SerializedName("name")
    var name = ""

    @SerializedName("number")
    var number = 20208
    //지은이 학번

    @SerializedName("stay_value")
    var stayState: Int = 4

}