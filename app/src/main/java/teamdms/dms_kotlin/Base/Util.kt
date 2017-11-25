package team_dms.dms.Base

import android.content.Context
import android.widget.Toast

/**
 * Created by root1 on 2017. 11. 23..
 */
object Util {

    fun showToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}