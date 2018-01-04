package teamdms.dms_kotlin.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by dsm2017 on 2018-01-02.
 */
class SurveyModel : Serializable{
    @SerializedName("end_date") var endDate : String? = null
    @SerializedName("start_date") var startDate : String? = null
    @SerializedName("is_objective") var isObjective : Boolean? = null
    @SerializedName("id") var id : String = ""
    @SerializedName("title") var title : String = ""
    @SerializedName("description") var desc : String? =null
}


