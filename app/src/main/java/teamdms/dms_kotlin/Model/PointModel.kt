package teamdms.dms_kotlin.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by dsm2017 on 2018-01-02.
 */
class PointModel : Serializable{
    @SerializedName("point") var point : Int? = 0
    @SerializedName("point_type") var pointType : Boolean? = null
    @SerializedName("reason") var reason : String? = null
    @SerializedName("time") var time : String = ""
}


