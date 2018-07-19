package teamdms.dms_kotlin.Model

import com.google.gson.annotations.*

/**
 * Created by dsm2016 on 2017-12-18.
 */

data class NoticeModel(@SerializedName("id") var id: String,
                       @SerializedName("title") var title: String,
                       @SerializedName("author") var author: String?,
                       @SerializedName("content") var content: String?,
                       @SerializedName("writeTime") var write_date: String)