package teamdms.dms_kotlin.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by dsm2017 on 2018-01-02.
 */
data class SurveyModel(@SerializedName("end_date") var endDate : String = "",
                       @SerializedName("start_date") var startDate : String = "",
                       @SerializedName("id") var id : String = "",
                       @SerializedName("title") var title : String = "",
                       @SerializedName("choice_pager") var choices : ArrayList<String>)


