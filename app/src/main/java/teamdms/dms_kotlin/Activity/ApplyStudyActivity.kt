package teamdms.dms_kotlin.Activity

import android.os.*
import team_dms.dms.Base.*
import teamdms.dms_kotlin.*

/**
 * Created by root1 on 2017. 12. 1..
 */
class ApplyStudyActivity: BaseActivity() {

    val tempData = Array<IntArray>(4, { IntArray(4) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_study)


    }

}