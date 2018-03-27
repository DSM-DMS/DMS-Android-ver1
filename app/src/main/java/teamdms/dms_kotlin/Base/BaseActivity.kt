package team_dms.dms.Base

import android.support.v7.app.*

/**
 * Created by root1 on 2017. 11. 23..
 */

open class BaseActivity : AppCompatActivity() {

    fun showToast(message: String) {
        Util.showToast(this, message)
    }

    fun saveToken(token: String, isAccess: Boolean = true) = Util.saveToken(this, token, isAccess)

    fun getToken(isAccess: Boolean = true): String = Util.getToken(this, isAccess)

}