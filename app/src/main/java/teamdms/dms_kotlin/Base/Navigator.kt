package teamdms.dms_kotlin.Base

import android.content.Intent

interface Navigator {
    fun finishActivity()

    fun nextActivity(intent: Intent)
}