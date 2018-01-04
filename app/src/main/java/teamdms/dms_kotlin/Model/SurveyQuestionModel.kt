package teamdms.dms_kotlin.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by dsm2017 on 2018-01-04.
 */
class SurveyQuestionModel : Serializable {

    @SerializedName("choice_paper") var choices : ArrayList<String>? = null
    @SerializedName("id") var id : String = ""
    @SerializedName("is_objective") var isObjective : Boolean = false
    @SerializedName("title") var title : String = ""
}