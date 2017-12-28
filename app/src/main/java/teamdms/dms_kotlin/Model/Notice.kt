package teamdms.dms_kotlin.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by dsm2016 on 2017-12-18.
 */

class Notice : Serializable{
    @SerializedName("id")
    var id : String?=null
    @SerializedName("title")
    var title: String?=null
    @SerializedName("author")
    var author : String?=null
    @SerializedName("content")
    var content : String? =null
    @SerializedName("write_date")
    var writeDate : String? =null

}